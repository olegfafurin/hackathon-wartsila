package lsd.wheel.routing

import io.ktor.request.*

class UserRouting : Routing("user") {

    data class UserForm(
        val login: String,
        val password: String
    )

    override fun install() {
        TODO("Not yet implemented")
    }

}