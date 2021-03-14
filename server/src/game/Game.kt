package lsd.wheel.game

import lsd.wheel.game.Direction.Companion.rotate
import lsd.wheel.service.data.User
import lsd.wheel.service.data.game.Edge
import lsd.wheel.service.data.game.Field
import lsd.wheel.service.data.game.Vertex

class Game(
    private val field: Field,
    private val users: MutableList<User>
) {

    inner class Player(
        val user: User,
        var vertexNo: Int,
        var direction: Direction
    )

    fun addUser(user: User) {
        users.add(user)
    }

    fun getPlayer(user: User): Player? {
        return userToPlayer[user]
    }

    private val userToPlayer: MutableMap<User, Player> = mutableMapOf()

    private val knownVertices: MutableMap<User, MutableSet<Int>> = mutableMapOf()
    private val knownEdges: MutableMap<User, MutableSet<Int>> = mutableMapOf()

    private fun getCurrentVertex(user: User) = field.vertices[userToPlayer[user]?.vertexNo!!]

    private fun getEdges(vertex: Vertex): List<Edge> = vertex.edges.values.map { field.edges[it] }

    private fun getNeighbours(vertex: Vertex): List<Vertex> =
        getEdges(vertex).map { field.vertices[it.vertex1 + it.vertex2 - vertex.id] }

    fun getKnownSubfield(user: User): Field {
        return Field(
            field.name,
            field.vertices.filter { knownVertices.getOrDefault(user, mutableSetOf()).contains(it.id) }
                .map {
                    return@map if (it.id == getCurrentVertex(user).id || it in getNeighbours(getCurrentVertex(user))) it else it.copy(
                        items = mutableListOf(),
                        edges = mutableMapOf()
                    )
                }, // TODO remove objects from nodes
            field.edges.filter { knownEdges.getOrDefault(user, mutableSetOf()).contains(it.id) })
    }

    private fun updateKnownVertices(user: User) {
        val currentVertex = getCurrentVertex(user)
        knownVertices[user]?.add(currentVertex.id)
        for (edge in getEdges(currentVertex)) {
            knownEdges[user]?.add(edge.id)
            knownVertices[user]?.add(edge.vertex1 + edge.vertex2 - currentVertex.id)
        }
    }

    fun makeMove(player: Player, direction: Direction): Boolean {
        val outgoingEdge = field.edges[field.vertices[player.vertexNo].edges[direction] ?: return false]
        val newVertexNo = outgoingEdge.vertex1 + outgoingEdge.vertex2 - player.vertexNo
        player.vertexNo = newVertexNo
        val incomingDirection =
            field.vertices[newVertexNo].edges.filterValues { it == outgoingEdge.id }.keys.first() // the only edge equals to current
        player.direction = incomingDirection.rotate(2)
        return true
    }

}