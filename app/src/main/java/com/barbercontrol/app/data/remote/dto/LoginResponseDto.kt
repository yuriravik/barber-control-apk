package com.barbercontrol.app.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/** Resposta da API após login bem-sucedido. */
@JsonClass(generateAdapter = true)
data class LoginResponseDto(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "token_type") val tokenType: String = "Bearer",
    @Json(name = "expires_in") val expiresIn: Int? = null
)
