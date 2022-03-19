package vendor1.account.dto

import java.io.Serializable

data class Authentication(
    val username: String,
    val password: String
) : Serializable