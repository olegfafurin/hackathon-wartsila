package lsd.wheel.routing

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import lsd.wheel.game.GameManager
import lsd.wheel.service.data.User

/**
 * created by imd on 14.03.2021
 */

class GameRouting(endpoint: String) : Routing(endpoint) {

    private val gameManager = GameManager
    private val objectMapper = ObjectMapper()

    override val install: Route.() -> Unit = {
        route("/$endpoint") {
            authenticate {
                post("/create-room") {
                    val user = call.principal<User>()!!
                    val roomName = context.receive<String>()
                    gameManager.createGame(user, roomName)
                    val field = gameManager.getGameByUser(user)?.getKnownSubfield(user)!!
                    call.respond(field)
                }

                post("/find-room") {
                    val user = call.principal<User>()!!
                    val roomName = context.receive<String>()
                    gameManager.addUserToGame(user, roomName)
                    val field = gameManager.getGameByUser(user)?.getKnownSubfield(user)!!
                    call.respond(field)
                }

                get("/get-field") {
                    val user = call.principal<User>()!!
                    val game = gameManager.getGameByUser(user)
                    val field = game?.getKnownSubfield(user)
                    call.respond(field!!)
                }
            }
        }
    }

}   