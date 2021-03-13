package lsd.wheel.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*
import kotlin.random.Random

object JWTInstance {

    private const val secret = "chetire stanka"
    private const val issuer = "ferris wheel"
    private const val expiresIn = 4 * 60 * 60 * 1000

    private val random = Random.Default
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    private val expiresAt
        get() = Date(System.currentTimeMillis() + expiresIn)

    private fun issueCommonToken(login: String, subject: String) =
        JWT.create()
            .withSubject(subject)
            .withIssuer(issuer)
            .withClaim("login", login)

    fun issueAccessToken(login: String): String =
        issueCommonToken(login, "Authentication")
            .withExpiresAt(expiresAt)
            .sign(algorithm)

    fun issueRefreshToken(login: String): String =
        issueCommonToken(login, "Refresh")
            .withClaim("random", random.nextInt())
            .sign(algorithm)

}