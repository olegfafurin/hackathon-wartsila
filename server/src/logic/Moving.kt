package lsd.wheel.logic

/**
 * created by imd on 13.03.2021
 */

interface Moving {
    fun move(current: Node, dir: Direction): Pair<Node, Direction>?
}