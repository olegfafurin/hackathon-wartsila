package lsd.wheel.routing

import io.ktor.routing.*

abstract class Routing(val endpoint: String) {

    abstract val install: Route.() -> Unit

}