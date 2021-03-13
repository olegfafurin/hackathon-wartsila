package lsd.wheel.service.data.game

/**
 * created by imd on 13.03.2021
 */

class Vertex(val id: Int) {
    var east: Vertex? = null
    var north: Vertex? = null
    var west: Vertex? = null
    var south: Vertex? = null

    fun nextToDirection(dir: Direction) : Vertex? = when (dir) {
        Direction.EAST -> east
        Direction.NORTH -> north
        Direction.WEST -> west
        Direction.SOUTH -> south
    }
}