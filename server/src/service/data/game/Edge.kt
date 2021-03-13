package lsd.wheel.service.data.game

data class Edge(
    val vertex1: Int,
    val vertex2: Int,
    val blocked: Boolean,
    val path: List<Pair<Int, Int>>
)
