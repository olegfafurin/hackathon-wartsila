package lsd.wheel.game

import lsd.wheel.service.GameService
import lsd.wheel.service.data.User
import kotlin.random.Random

object GameManager {

    private val games: MutableMap<String, Game> = mutableMapOf()

    private val userGame: MutableMap<User, Game> = mutableMapOf()

    fun createGame(user: User, roomName: String): Boolean {
        if (roomName in games.keys) return false
        val newField = GameService.getFieldById(1) // TODO field generation
        val newGame = Game(newField, mutableListOf(user))
        games[roomName] = newGame
        userGame[user] = newGame
        return true
    }

    fun addUserToGame(user: User, roomName: String): Boolean {
        val game = games[roomName] ?: return false
        game.addUser(user)
        userGame[user] = game
        return true
    }

    fun getGameByUser(user: User): Game? = userGame[user]

}