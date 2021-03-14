package lsd.wheel.game

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
        val vertexNo: Int,
        val direction: Direction
    )

    fun addUser(user: User) {
        users.add(user)
    }

    private val userToPlayer: MutableMap<User, Player> = mutableMapOf()

    private val knownVertices: MutableMap<User, MutableSet<Int>> = mutableMapOf()
    private val knownEdges: MutableMap<User, MutableSet<Int>> = mutableMapOf()

    private fun updateKnownVertices(user: User) {
        val currentVertex = getCurrentVertex(user)
        knownVertices[user]?.add(currentVertex.id)
        for (edge in getEdges(currentVertex)) {
            knownEdges[user]?.add(edge.id)
            knownVertices[user]?.add(edge.vertex1 + edge.vertex2 - currentVertex.id)
        }
    }

    private fun getCurrentVertex(user: User) = field.vertices[userToPlayer[user]?.vertexNo!!]

    private fun getEdges(vertex: Vertex): List<Edge> = vertex.edges.values.map { field.edges[it] }

    private fun getNeighbours(vertex: Vertex): List<Vertex> =
        getEdges(vertex).map { field.vertices[it.vertex1 + it.vertex2 - vertex.id] }

    fun getKnownSubfield(user: User): Field {
        return Field(
            field.name,
            field.vertices.filter { knownVertices.getOrDefault(user, mutableSetOf()).contains(it.id) }, // TODO remove objects from nodes
            field.edges.filter { knownEdges.getOrDefault(user, mutableSetOf()).contains(it.id) })
    }


}