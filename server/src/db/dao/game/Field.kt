package lsd.wheel.db.dao.game

import org.jetbrains.exposed.dao.id.IntIdTable

object FieldTable: IntIdTable() {
    val name = varchar("name", 32).uniqueIndex()
}