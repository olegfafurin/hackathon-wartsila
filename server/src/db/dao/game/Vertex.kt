package lsd.wheel.db.dao.game

import db.dao.game.EdgeTable
import org.jetbrains.exposed.dao.id.IntIdTable

object VertexTable: IntIdTable() {
    val fieldId = integer("field_id").references(FieldTable.id)

    val x = integer("x")
    val y = integer("y")

    val edgeEast = integer("edge_east").references(EdgeTable.id)
    val edgeNorth = integer("edge_north").references(EdgeTable.id)
    val edgeWest = integer("edge_west").references(EdgeTable.id)
    val edgeSouth = integer("edge_south").references(EdgeTable.id)
}
