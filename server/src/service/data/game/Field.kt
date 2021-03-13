package lsd.wheel.service.data.game

data class Field(
    val name: String,
    val vertices: List<Vertex>,
    val edges: List<Edge>
)