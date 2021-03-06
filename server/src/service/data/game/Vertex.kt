package lsd.wheel.service.data.game

import lsd.wheel.game.Direction

data class Vertex(
    val id: Int,
    val x: Int,
    val y: Int,
    val items: MutableList<Item>,
    val edges: MutableMap<Direction, Int>
)