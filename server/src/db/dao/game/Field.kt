package lsd.wheel.db.dao.game

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object FieldTable : IntIdTable() {
    val name = varchar("name", 32).uniqueIndex()
}

class FieldEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FieldEntity>(FieldTable)

    val name by FieldTable.name
}