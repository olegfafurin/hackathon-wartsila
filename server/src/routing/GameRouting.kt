package lsd.wheel.routing

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import lsd.wheel.game.Direction
import lsd.wheel.game.GameManager
import lsd.wheel.service.data.User

/**
 * created by imd on 14.03.2021
 */

class GameRouting(endpoint: String) : Routing(endpoint) {

    private val gameManager = GameManager
    private val objectMapper = ObjectMapper()

    override val install: Route.() -> Unit = {
        authenticate {
            post("/create-room") {
                val user = call.principal<User>()!!
                val roomName = context.receive<String>()
                val gameCreated = gameManager.createGame(user, roomName)
                call.respond(
                    mapOf(
                        "status" to if (gameCreated) "OK" else "ERROR",
                    )
                )
            }

            post("/find-room") {
                val user = call.principal<User>()!!
                val roomName = context.receive<String>()
                val roomFound = gameManager.addUserToGame(user, roomName)
                call.respond(
                    mapOf(
                        "status" to if (roomFound) "OK" else "ERROR",
                    )
                )
            }

            get("/field") {
                val user = call.principal<User>()!!
                val knownField = gameManager.getGameByUser(user)?.getKnownSubfield(user) ?: call.respond(
                    mapOf(
                        "status" to "ERROR",
                    )
                )
                call.respond(
                    mapOf(
                        "status" to "OK",
                        "field" to knownField
                    )
                )
            }

            post("/move") {
                val user = call.principal<User>()!!
                val moveDirection = context.receive<Direction>()
                val game = gameManager.getGameByUser(user)
                if (game == null) {
                    call.respond(
                        mapOf(
                            "status" to "ERROR",
                        )
                    )
                    return@post
                }
                val player = game.getPlayer(user)!!
                game.makeMove(player, moveDirection)
                call.respond(
                    mapOf(
                        "status" to "OK",
                    )
                )
            }
        }
    }

}   