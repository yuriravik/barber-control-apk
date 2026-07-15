package com.barbercontrol.app.core.util

/**
 * Constantes globais da aplicação.
 */
object Constants {

    /** Chave usada para armazenar o token de acesso no DataStore. */
    const val TOKEN_KEY = "auth_token"

    /** Nome do arquivo DataStore de preferências. */
    const val PREFERENCES_NAME = "barber_control_prefs"

    /** Nome do banco de dados local Room. */
    const val DATABASE_NAME = "barber_control_db"

    /** Tempo de timeout para requisições de rede (segundos). */
    const val NETWORK_TIMEOUT_SECONDS = 30L
}
