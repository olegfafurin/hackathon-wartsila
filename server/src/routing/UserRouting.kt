package lsd.wheel.routing

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import lsd.wheel.auth.JWTInstance
import lsd.wheel.service.GameService
import lsd.wheel.service.UserService
import lsd.wheel.service.data.User

class UserRouting(endpoint: String) : Routing(endpoint) {

    data class UserForm(
        val login: String,
        val password: String
    )

    private val service = UserService

    override val install: Route.() -> Unit = {
        route("/$endpoint") {
            post("/login") {
                val userData = context.receive<UserForm>()
                if (!service.checkUserCredentials(userData.login, userData.password)) {
                    call.respond(HttpStatusCode.Unauthorized)
                } else {
                    call.respond(mapOf(
                        "login" to userData.login,
                        "accessToken" to JWTInstance.issueAccessToken(userData.login),
                        "refreshToken" to JWTInstance.issueRefreshToken(userData.login)
                    ))
                }
            }

            authenticate {
                post("/logout") {
                    val user = call.principal<User>()
                    TODO("need to remove user from all sessions")
                }
            }

            post("/register") {
                val userData = context.receive<UserForm>()
                if (service.createUser(userData.login, userData.password)) call.respond("ОК")
                else call.respond("User already exists")
            }

            get("/test-field") {
                call.respond(GameService.getFieldById(1))
            }
        }
    }

}