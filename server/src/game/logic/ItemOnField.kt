package lsd.wheel.game.logic

import lsd.wheel.service.data.game.Direction
import lsd.wheel.service.data.game.Vertex

abstract class ItemOnField {

    abstract var location: Vertex
    abstract var direction: Direction

    abstract fun move(dir: Direction)

}