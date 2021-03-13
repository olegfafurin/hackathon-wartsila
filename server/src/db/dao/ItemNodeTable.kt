package lsd.wheel.db.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * created by imd on 13.03.2021
 */

object EdgePathTable: IntIdTable() {
    val edgeId = integer("edgeId").uniqueIndex().references(EdgeVertexTable.edgeId)
    val pathIndex = integer("pathIndex")
    val x = integer("x")
    val y = integer("y")
}

object EdgeVertexTable: IntIdTable() {
    val edgeId = integer("edgeId").uniqueIndex()
    val vertexId = integer("vertexId").references(NodeFieldTable.vertexId)
}

object NodeFieldTable: IntIdTable() {
    val fieldId = integer("fieldId")
    val vertexId = integer("vertexId").uniqueIndex()
    val x = integer("x")
    val y = integer("y")
    val edgeEast = integer("edgeEast").references(EdgeVertexTable.edgeId)
    val edgeNorth = integer("edgeNorth").references(EdgeVertexTable.edgeId)
    val edgeWest = integer("edgeWest").references(EdgeVertexTable.edgeId)
    val edgeSouth = integer("edgeSouth").references(EdgeVertexTable.edgeId)
}



class ItemTable : IntIdTable() {
    val itemId = integer("itemId")
    val vertexId = integer("vertexId")
    val type = varchar("type", 20)
    val value = integer("value")
}