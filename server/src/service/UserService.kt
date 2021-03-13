package lsd.wheel.service

import lsd.wheel.auth.CryptoHash
import lsd.wheel.db.dao.UserEntity
import lsd.wheel.db.dao.UserTable
import lsd.wheel.service.data.User
import org.jetbrains.exposed.sql.and

object UserService {

    fun getUserByLogin(login: String): User? =
        UserEntity.find { UserTable.login eq login }.firstOrNull()?.let {
            User(it.login, it.passwordHash)
        }

    fun checkUserCredentials(login: String, password: String): Boolean =
        !UserEntity.find { (UserTable.login eq login) and (UserTable.passwordHash eq CryptoHash.encode(password)) }
            .empty()

    fun addUser(userLogin: String, password: String): Boolean {
        return if (UserEntity.find { UserTable.login eq userLogin }.empty()) {
            UserEntity.new {
                login = userLogin
                passwordHash = CryptoHash.encode(password)
                rating = 0
            }
            true
        } else false
    }
}