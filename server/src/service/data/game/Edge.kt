package lsd.wheel.service.data.game

data class Edge(
    val id: Int,
    val vertex1: Int,
    val vertex2: Int,
    val blocked: Boolean,
    val path: MutableList<Pair<Int, Int>>
)
