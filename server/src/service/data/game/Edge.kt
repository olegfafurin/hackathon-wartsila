package lsd.wheel.service.data.game

data class Edge(
    val id: Int,
    val vertex1: Int,
    val vertex2: Int,
    val blocked: Boolean,
    var path: List<Pair<Int, Int>>
)
