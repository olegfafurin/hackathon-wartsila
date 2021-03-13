package lsd.wheel.service.data

import io.ktor.auth.*

data class User(
    val login: String,
    val passwordHash: String
) : Principal
