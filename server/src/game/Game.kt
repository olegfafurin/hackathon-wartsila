package lsd.wheel.game

import lsd.wheel.game.Direction.Companion.rotate
import lsd.wheel.service.data.User
import lsd.wheel.service.data.game.Edge
import lsd.wheel.service.data.game.Field
import lsd.wheel.service.data.game.Item
import lsd.wheel.service.data.game.Vertex
import kotlin.random.Random

class Game(
    private val field: Field,
    private val players: MutableList<Player>
) {

    inner class Player(
        val username: String,
        var vertexNo: Int,
        var direction: Direction,
        var balance: Int = 0,
        val items: MutableList<Item> = mutableListOf()
    )

    fun addPlayer(username: String) {
        val newPlayer = Player(username, Random(System.nanoTime()).nextInt(field.vertices.size), Direction.NORTH)
        knownVertices[newPlayer] = mutableSetOf()
        knownEdges[newPlayer] = mutableSetOf()
        players.add(newPlayer)
        usernameToPlayer.putIfAbsent(username, newPlayer)
        updateKnownVertices(newPlayer)
    }

    val usernameToPlayer: MutableMap<String, Player> = mutableMapOf()

    private val knownVertices: MutableMap<Player, MutableSet<Int>> = mutableMapOf()
    private val knownEdges: MutableMap<Player, MutableSet<Int>> = mutableMapOf()

    fun getCurrentVertex(player: Player) = field.vertices[player.vertexNo]

    private fun getEdges(vertex: Vertex): List<Edge> = vertex.edges.values.map { field.edges[it] }

    private fun getNeighbours(vertex: Vertex): List<Vertex> =
        getEdges(vertex).map { field.vertices[it.vertex1 + it.vertex2 - vertex.id] }

    fun getKnownSubfield(player: Player): Field {
        return Field(
            field.name,
            field.vertices.filter { knownVertices.getOrDefault(player, mutableSetOf()).contains(it.id) }
                .map {
                    return@map if (it.id == getCurrentVertex(player).id || it in getNeighbours(getCurrentVertex(player))) it else it.copy(
                        items = mutableListOf(),
                        edges = mutableMapOf()
                    )
                }, // TODO remove objects from nodes
            field.edges.filter { knownEdges.getOrDefault(player, mutableSetOf()).contains(it.id) })
    }

    private fun updateKnownVertices(player: Player) {
        val currentVertex = getCurrentVertex(player)
        knownVertices.getValue(player).add(currentVertex.id)
        for (edge in getEdges(currentVertex)) {
            knownEdges.getValue(player).add(edge.id)
            knownVertices.getValue(player).add(edge.vertex1 + edge.vertex2 - currentVertex.id)
        }
    }

    fun makeMove(player: Player, direction: Direction): Boolean {
        val outgoingEdge = field.edges[field.vertices[player.vertexNo].edges[direction] ?: return false]
        val newVertexNo = outgoingEdge.vertex1 + outgoingEdge.vertex2 - player.vertexNo
        player.vertexNo = newVertexNo
        val incomingDirection =
            field.vertices[newVertexNo].edges.filterValues { it == outgoingEdge.id }.keys.first() // the only edge equals to current
        player.direction = incomingDirection.rotate(2)
        updateKnownVertices(player)
        return true
    }

}