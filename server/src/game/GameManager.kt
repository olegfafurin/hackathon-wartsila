package lsd.wheel.game

import lsd.wheel.service.GameService
import lsd.wheel.service.data.User

object GameManager {

    private val games: MutableMap<String, Game> = mutableMapOf()

    private val usernameGame: MutableMap<String, Game> = mutableMapOf()

    fun createGame(username: String, roomName: String): Boolean {
        if (roomName in games.keys) return false
        val newField = GameService.getFieldById(7) // TODO field generation
        val newGame = Game(newField, mutableListOf())
        games[roomName] = newGame
        addUserToGame(username, roomName)
        return true
    }

    fun addUserToGame(username: String, roomName: String): Boolean {
        val game = games[roomName] ?: return false
        game.addPlayer(username)
        usernameGame[username] = game
        return true
    }

    fun getGameByUsername(username: String): Game? = usernameGame[username]

}