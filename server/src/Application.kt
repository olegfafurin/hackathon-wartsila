package lsd.wheel

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.sessions.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.auth.*
import com.fasterxml.jackson.databind.*
import com.zaxxer.hikari.HikariConfig
import io.ktor.auth.jwt.*
import io.ktor.jackson.*
import lsd.wheel.auth.JWTInstance
import lsd.wheel.db.initDatabase
import lsd.wheel.routing.UserRouting
import lsd.wheel.service.GameService
import lsd.wheel.service.UserService

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Put)
        method(HttpMethod.Post)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)

        header(HttpHeaders.Authorization)
        header(HttpHeaders.AccessControlAllowOrigin)
        header(HttpHeaders.AccessControlAllowHeaders)

        allowCredentials = true
        allowNonSimpleContentTypes = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(Authentication) {
        jwt {
            verifier(JWTInstance.verifier)
            realm = "***.wheel"
            validate { credentials ->
                val login = credentials.payload.getClaim("login")
                if (login.isNull) null else UserService.getUserByLogin(login.asString())
            }
        }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    initDatabase(HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/game"
        driverClassName = "org.postgresql.Driver"
        username = "postgres"
        password = "postgres"
        maximumPoolSize = 4
        transactionIsolation = "TRANSACTION_READ_COMMITTED"
    })

    routing {
        get("/") {
            call.respond("Ok")
        }

        (UserRouting("user").install)()
    }

    GameService.getFieldById(1)
}

