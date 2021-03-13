package lsd.wheel.logic

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

        fun byValue(value: Int): Direction = VALUES.first { it.id == value }

        fun Direction.rotate(n: Int): Direction = byValue((this.id + n) % 4)
    }
}
