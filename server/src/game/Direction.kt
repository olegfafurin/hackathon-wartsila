package lsd.wheel.game

/**
 * created by imd on 13.03.2021
 */

enum class Direction(val id: Int) {

    EAST(0),
    NORTH(1),
    WEST(2),
    SOUTH(3);

    companion object {
        private val VALUES = values()

        fun by(value: Int): Direction = VALUES.first { it.id == value }

        fun Direction.rotate(n: Int): Direction = by((this.id + n) % 4)
    }

}
