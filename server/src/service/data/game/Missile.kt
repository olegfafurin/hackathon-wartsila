package lsd.wheel.service.data.game

import lsd.wheel.game.logic.ItemOnField
import lsd.wheel.service.data.game.Direction.Companion.rotate

/**
 * created by imd on 13.03.2021
 */

class Missile(override val itemId: Int) : Item, ItemOnField {

    /**
     * No same nodes between the neighbours of current one required.
     */
    override fun move(current: Vertex, dir: Direction): Pair<Vertex, Direction>? {
        val newNode = current.nextToDirection(dir) ?: return null
        val incomingDir =
            listOf(0, 1, 2, 3).map { Direction.byValue(it) }.first { newNode.nextToDirection(it) == current }
        val newDirection = incomingDir.rotate(2)
        return Pair(newNode, newDirection)
    }
}