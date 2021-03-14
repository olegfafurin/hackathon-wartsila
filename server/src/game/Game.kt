package lsd.wheel.game

import lsd.wheel.service.data.User
import lsd.wheel.service.data.game.Field

class Game(
    private val field: Field,
    private val users: List<User>
) {

    inner class Player(
        val user: User,
        val vertexNo: Int
    )

    private val players: MutableList<Player> = mutableListOf()

}