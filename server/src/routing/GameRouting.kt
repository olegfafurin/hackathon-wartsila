package lsd.wheel.routing

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import lsd.wheel.game.Direction
import lsd.wheel.game.GameManager
import lsd.wheel.service.data.User
import lsd.wheel.service.data.game.Babah
import lsd.wheel.service.data.game.MineItem
import lsd.wheel.service.data.game.Missile

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

                get("/get-field") {
                    val user = call.principal<User>()!!
                    val game = gameManager.getGameByUsername(user.login)
                    if (game == null) {
                        call.respond(mapOf("status" to "ERROR"))
                        return@get
                    }
                    val player = game.usernameToPlayer[user.login]!!
                    if (game.isDead(player)) {
                        game.removePlayer(player)
                        call.respond(mapOf("status" to "GAME_OVER"))
                        return@get
                    }
                    if (!game.isCurrentPlayer(player)) {
                        call.respond(mapOf("status" to "Not your turn"))
                        return@get
                    }
                    val knownField = game.getKnownSubfield(player)
                    call.respond(
                        mapOf(
                            "status" to "OK",
                            "field" to knownField,
                            "currentPoint" to game.getCurrentVertex(player),
                            "currentDirection" to player.direction,
                            "playerHealth" to player.health,
                            "playerItems" to mapOf(
                                "mines" to player.items.count { it is MineItem },
                                "missiles" to player.items.count { it is Missile }
                            ),
                            "neighbors" to game.neighboringPlayers(player)
                        )
                    )
                }
                post("/set-mine") {
                    val user = call.principal<User>()!!
                    val game = gameManager.getGameByUsername(user.login)!!
                    val player = game.usernameToPlayer[user.login]!!
                    if (game.isDead(player)) {
                        game.removePlayer(player)
                        call.respond(mapOf("status" to "GAME_OVER"))
                        return@post
                    }
                    if (!game.isCurrentPlayer(player)) {
                        call.respond(mapOf("status" to "Not your turn"))
                        return@post
                    }
                    if (!player.items.any { it is MineItem }) {
                        call.respond(mapOf("status" to "No mine available"))
                        return@post
                    }
                    player.items.removeAt(player.items.indexOfFirst { it is MineItem })
                    game.getCurrentVertex(player).items.add(Babah())
                    call.respond(
                        mapOf(
                            "status" to "OK",
                        )
                    )
                    game.nextPlayer()
                }

                post("/move") {
                    val user = call.principal<User>()!!
                    val game = gameManager.getGameByUsername(user.login)
                    if (game == null) {
                        call.respond(mapOf("status" to "ERROR"))
                        return@post
                    }
                    val player = game.usernameToPlayer[user.login]!!
                    if (game.isDead(player)) {
                        game.removePlayer(player)
                        call.respond(mapOf("status" to "GAME_OVER"))
                        return@post
                    }
                    if (!game.isCurrentPlayer(player)) {
                        call.respond(mapOf("status" to "Not your turn"))
                        return@post
                    }
                    val ok = game.makeMove(player, player.direction)
                    if (!ok) {
                        call.respond(
                            mapOf(
                                "status" to "OK",
                                "blocked" to true
                            )
                        )
                        return@post
                    }
                    if (game.isDead(player)) {
                        game.removePlayer(player)
                        call.respond(mapOf("status" to "GAME_OVER"))
                        return@post
                    }
                    call.respond(
                        mapOf(
                            "status" to "OK",
                        )
                    )
                    game.nextPlayer()
                }

                post("/rotate") {
                    val user = call.principal<User>()!!
                    val quarters = call.receive<Int>()
                    val game = gameManager.getGameByUsername(user.login)
                    if (game == null) {
                        call.respond(mapOf("status" to "ERROR"))
                        return@post
                    }
                    val player = game.usernameToPlayer[user.login]!!
                    if (game.isDead(player)) {
                        game.removePlayer(player)
                        call.respond(mapOf("status" to "GAME_OVER"))
                        return@post
                    }
                    if (!game.isCurrentPlayer(player)) {
                        call.respond(mapOf("status" to "Not your turn"))
                        return@post
                    }
                    player.direction = Direction.by((player.direction.id + quarters + 4) % 4)
                    call.respond(
                        mapOf(
                            "status" to "OK",
                        )
                    )
                }

                post("/fire") {
                    val user = call.principal<User>()!!
                    val game = gameManager.getGameByUsername(user.login)
                    if (game == null) {
                        call.respond(mapOf("status" to "ERROR"))
                        return@post
                    }
                    val player = game.usernameToPlayer[user.login]!!
                    if (game.isDead(player)) {
                        game.removePlayer(player)
                        call.respond(mapOf("status" to "GAME_OVER"))
                        return@post
                    }
                    if (!game.isCurrentPlayer(player)) {
                        call.respond(mapOf("status" to "Not your turn"))
                        return@post
                    }
                    if (!player.items.any { it is Missile }) {
                        call.respond(mapOf("status" to "No missiles available"))
                        return@post
                    }
                    player.items.removeAt(player.items.indexOfFirst { it is Missile })
                    call.respond(
                        mapOf(
                            "status" to "OK",
                            "success" to player.fire()
                        )
                    )
                    game.nextPlayer()
                }

            }
        }
    }

}