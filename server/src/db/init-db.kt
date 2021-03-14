package lsd.wheel.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import db.dao.game.EdgePathTable
import db.dao.game.EdgeTable
import lsd.wheel.db.dao.UserTable
import lsd.wheel.db.dao.game.FieldTable
import lsd.wheel.db.dao.game.VertexTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.createMissingTablesAndColumns
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

fun initDatabase(config: HikariConfig): Database {
    val hikari = HikariDataSource(config)
    val db = Database.connect(hikari)

    transaction {
        createMissingTablesAndColumns(
            UserTable,
            FieldTable, VertexTable, EdgeTable, EdgePathTable
        )
    }

    for (constraint in listOf(
        Pair("edge", "fk_edge_vertex_1_id_id"),
        Pair("edge", "fk_edge_vertex_2_id_id"),
        Pair("vertex", "fk_vertex_edge_east_id"),
        Pair("vertex", "fk_vertex_edge_north_id"),
        Pair("vertex", "fk_vertex_edge_west_id"),
        Pair("vertex", "fk_vertex_edge_south_id")
    )) {
        transaction {
            val connection = TransactionManager.current().connection
            val statement = connection.prepareStatement(
                "ALTER TABLE ${constraint.first} " +
                        "ALTER CONSTRAINT ${constraint.second} DEFERRABLE INITIALLY DEFERRED;", false
            )
            statement.executeBatch()
        }
    }

    return db
}

fun initDatabase(properties: String): Database {
    val config = HikariConfig(properties)
    return initDatabase(config)
}