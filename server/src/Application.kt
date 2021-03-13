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
import io.ktor.auth.jwt.*
import io.ktor.jackson.*
import lsd.wheel.auth.JWTInstance
import lsd.wheel.db.initDatabase
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
                when {
                    login.isNull -> null
                    UserService.getUserByLogin() == null -> null
                    else -> JWTPrincipal(credentials.payload)
                }
            }
        }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    initDatabase("resources/db.properties")
}

