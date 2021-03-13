package db.dao.game

import lsd.wheel.db.dao.game.VertexTable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

object EdgeTable : IntIdTable() {
    val blocked = bool("blocked")
    val vertex1Id = integer("vertex_1_id").references(VertexTable.id)
    val vertex2Id = integer("vertex_2_id").references(VertexTable.id)
}

object EdgePathTable : Table() {
    val edgeId = integer("edge_id").references(EdgeTable.id)
    val pathIndex = integer("path_index")
    val x = integer("x")
    val y = integer("y")

    override val primaryKey = PrimaryKey(edgeId, pathIndex, name = "id")
}