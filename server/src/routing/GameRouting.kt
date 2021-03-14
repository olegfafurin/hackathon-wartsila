package lsd.wheel.routing

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
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
        authenticate {
            post("/create-room") {
                val user = call.principal<User>()!!
                val roomName = context.receive<String>()
                val gameCreated = gameManager.createGame(user.login, roomName)
                call.respond(
                    mapOf(
                        "status" to if (gameCreated) "OK" else "ERROR",
                    )
                )
            }

            post("/find-room") {
                val user = call.principal<User>()!!
                val roomName = context.receive<String>()
                val roomFound = gameManager.addUserToGame(user.login, roomName)
                call.respond(
                    mapOf(
                        "status" to if (roomFound) "OK" else "ERROR",
                    )
                )
            }

            get("/field") {
                val user = call.principal<User>()!!
                val game = gameManager.getGameByUsername(user.login)
                if (game == null) {
                    call.respond(mapOf("status" to "ERROR"))
                    return@get
                }
                val player = game.usernameToPlayer[user.login]!!
                val knownField = game.getKnownSubfield(player)
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
                val game = gameManager.getGameByUsername(user.login)
                if (game == null) {
                    call.respond(mapOf("status" to "ERROR"))
                    return@post
                }
                val player = game.usernameToPlayer[user.login]!!
                game.makeMove(player, moveDirection)
                call.respond(
                    mapOf(
                        "status" to "OK",
                    )
                )
            }
            post("/move") {

            }

            get("/get-field") {
                val user = call.principal<User>()!!
                val game = gameManager.getGameByUsername(user.login)
                val player = game.usernameToPlayer[user.login]!!
                val field = game?.getKnownSubfield(player)
                call.respond(field!!)
            }
        }
    }

}   