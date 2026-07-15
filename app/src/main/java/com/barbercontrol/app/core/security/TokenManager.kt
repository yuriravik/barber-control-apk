package com.barbercontrol.app.core.security

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.barbercontrol.app.core.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Gerencia o token de autenticação do usuário usando DataStore Preferences.
 *
 * O DataStore é a alternativa moderna e segura ao SharedPreferences,
 * com suporte a coroutines e Flow.
 */
@Singleton
class TokenManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val tokenKey = stringPreferencesKey(Constants.TOKEN_KEY)

    /** Retorna um Flow com o token armazenado (null se não existir). */
    fun getToken(): Flow<String?> = dataStore.data.map { prefs ->
        prefs[tokenKey]
    }

    /** Salva o token de acesso localmente. */
    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    /** Remove o token (logout). */
    suspend fun clearToken() {
        dataStore.edit { prefs ->
            prefs.remove(tokenKey)
        }
    }
}
