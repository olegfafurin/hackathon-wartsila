package lsd.wheel.service.data.game

/**
 * created by imd on 13.03.2021
 */

class Field(val size: Int) {
    val vertices: List<Vertex> = List(size) { Vertex(it) }

    init {

    }
}