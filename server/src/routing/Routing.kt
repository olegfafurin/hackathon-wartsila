package lsd.wheel.routing

abstract class Routing(val endpoint: String) {

    abstract fun install()

}