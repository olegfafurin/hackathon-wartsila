package lsd.wheel.logic

/**
 * created by imd on 13.03.2021
 */

class Node(val id: Int) {
    var east: Node? = null
    var north: Node? = null
    var west: Node? = null
    var south: Node? = null

    fun nextToDirection(dir: Direction) : Node? = when (dir) {
        Direction.EAST -> east
        Direction.NORTH -> north
        Direction.WEST -> west
        Direction.SOUTH -> south
    }

//    var players: List<Player>
}