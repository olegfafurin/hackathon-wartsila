package lsd.wheel.logic

import lsd.wheel.logic.Direction.Companion.rotate

/**
 * created by imd on 13.03.2021
 */

class Missile(override val itemId: Int) : Item, Moving {

    /**
     * No same nodes between the neighbours of current one required.
     */
    override fun move(current: Node, dir: Direction): Pair<Node, Direction>? {
        val newNode = current.nextToDirection(dir) ?: return null
        val incomingDir =
            listOf(0, 1, 2, 3).map { Direction.byValue(it) }.first { newNode.nextToDirection(it) == current }
        val newDirection = incomingDir.rotate(2)
        return Pair(newNode, newDirection)
    }
}