package lsd.wheel.db.dao.game

import org.jetbrains.exposed.dao.id.IntIdTable

object ItemTable : IntIdTable() {
    val vertexId = integer("vertex_id").references(VertexTable.id)
    val type = varchar("type", 32)
    val value = integer("value")
}