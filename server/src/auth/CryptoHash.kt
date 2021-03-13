package lsd.wheel.auth

import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object CryptoHash {

    private val hasher = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    private val salt = SecureRandom().generateSeed(16)
    private val base64Encoder = Base64.getEncoder()

    fun encode(value: String): String {
        val keySpec = PBEKeySpec(value.toCharArray(), salt, 64, 128)
        val hash = hasher.generateSecret(keySpec).encoded
        return base64Encoder.encodeToString(hash)
    }

    fun validate(value: String, hash: String): Boolean {
        return encode(value) == hash
    }

}