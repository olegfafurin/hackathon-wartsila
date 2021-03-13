package lsd.wheel.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import lsd.wheel.db.dao.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.createMissingTablesAndColumns
import org.jetbrains.exposed.sql.transactions.transaction

fun initDatabase(config: HikariConfig): Database {
    val hikari = HikariDataSource(config)
    val db = Database.connect(hikari)

    transaction {
        // TODO add all tables
        createMissingTablesAndColumns(UserTable)
    }

    return db
}

fun initDatabase(properties: String): Database {
    val config = HikariConfig(properties)
    return initDatabase(config)
}