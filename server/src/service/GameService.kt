package lsd.wheel.service

import db.dao.game.EdgePathTable
import db.dao.game.EdgeTable
import lsd.wheel.db.dao.game.FieldEntity
import lsd.wheel.db.dao.game.VertexTable
import lsd.wheel.game.Direction
import lsd.wheel.service.data.game.Edge
import lsd.wheel.service.data.game.Field
import lsd.wheel.service.data.game.Vertex
import org.jetbrains.exposed.sql.Join
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object GameService {

    fun getFieldById(id: Int): Field = transaction {
        val fieldEntity = FieldEntity[id]
        val vertexRows = VertexTable.select { VertexTable.fieldId eq fieldEntity.id.value }
        val edgeRows = Join(
            EdgeTable, VertexTable,
            JoinType.LEFT,
            onColumn = EdgeTable.vertex1Id, otherColumn = VertexTable.id
        ).select { VertexTable.fieldId eq fieldEntity.id.value }

        val vertexRowList = mutableListOf<ResultRow>()
        val vertexMapper = mutableMapOf<Int, Int>()
        val vertices = mutableListOf<Vertex>()
        vertexRows.forEach { row ->
            val vertexId = row[VertexTable.id].value
            vertexMapper[vertexId] = vertices.size
            vertexRowList.add(row)
            vertices.add(
                Vertex(
                    vertices.size,
                    row[VertexTable.x],
                    row[VertexTable.y],
                    mutableListOf(),
                    mutableMapOf()
                )
            )
        }

        val edgeMapper = mutableMapOf<Int, Int>()
        val edges = mutableListOf<Edge>()
        edgeRows.forEach { row ->
            val vOld1 = row[EdgeTable.vertex1Id]
            val vOld2 = row[EdgeTable.vertex2Id]
            val edgeOldId = row[EdgeTable.id].value

            val v1 = vertexMapper[vOld1] ?: error("vertex not found")
            val v2 = vertexMapper[vOld2] ?: error("vertex not found")
            val edgeId = edges.size
            edges.add(
                Edge(
                    edgeId,
                    v1, v2,
                    row[EdgeTable.blocked],
                    EdgePathTable.select { EdgePathTable.edgeId eq edgeOldId }.map { pathRow ->
                        Pair(pathRow[EdgePathTable.x], pathRow[EdgePathTable.y])
                    }
                )
            )
            when {
                vertexRowList[v1][VertexTable.edgeEast] == edgeOldId -> {
                    vertices[v1].edges[Direction.EAST] = edgeId
                    vertices[v2].edges[Direction.WEST] = edgeId
                }
                vertexRowList[v1][VertexTable.edgeWest] == edgeOldId -> {
                    vertices[v1].edges[Direction.WEST] = edgeId
                    vertices[v2].edges[Direction.EAST] = edgeId
                }
                vertexRowList[v1][VertexTable.edgeNorth] == edgeOldId -> {
                    vertices[v1].edges[Direction.NORTH] = edgeId
                    vertices[v2].edges[Direction.SOUTH] = edgeId
                }
                vertexRowList[v1][VertexTable.edgeSouth] == edgeOldId -> {
                    vertices[v1].edges[Direction.SOUTH] = edgeId
                    vertices[v2].edges[Direction.NORTH] = edgeId
                }
            }
        }

        Field(
            fieldEntity.name,
            vertices, edges
        )
    }

}