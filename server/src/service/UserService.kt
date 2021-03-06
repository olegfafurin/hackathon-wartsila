package lsd.wheel.service

import lsd.wheel.auth.CryptoHash
import lsd.wheel.db.dao.UserEntity
import lsd.wheel.db.dao.UserTable
import lsd.wheel.service.data.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

object UserService {

    fun getUserByLogin(login: String): User? = transaction {
        UserEntity.find { UserTable.login eq login }.firstOrNull()?.let {
            User(it.login, it.passwordHash)
        }
    }

    fun checkUserCredentials(login: String, password: String): Boolean = transaction {
        !UserEntity.find { (UserTable.login eq login) and (UserTable.passwordHash eq CryptoHash.encode(password)) }
            .empty()
    }

    fun createUser(login: String, password: String): Boolean = transaction {
        if (UserEntity.find { UserTable.login eq login }.empty()) {
            UserEntity.new {
                this.login = login
                passwordHash = CryptoHash.encode(password)
                rating = 0
            }
            true
        } else {
            false
        }
    }

}