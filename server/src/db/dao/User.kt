package lsd.wheel.db.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable() {
    val login = varchar("login", 32).uniqueIndex()
    val passwordHash = varchar("password_hash", 256)
    val rating = integer("rating")
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(UserTable)

    var login by UserTable.login
    var passwordHash by UserTable.passwordHash
    var rating by UserTable.rating
}