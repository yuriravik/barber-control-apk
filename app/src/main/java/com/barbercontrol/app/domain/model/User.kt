package com.barbercontrol.app.domain.model

/**
 * Modelo de domínio que representa o usuário autenticado.
 *
 * Expanda conforme o contrato da API (ex: name, avatarUrl, role).
 */
data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",
    val role: String = "CLIENT" // "CLIENT" | "BARBER" | "ADMIN"
)
