package com.barbercontrol.app.core.network

import com.barbercontrol.app.core.security.TokenManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptador OkHttp responsável por adicionar o header de autenticação JWT
 * a todas as requisições que possuam um token salvo.
 *
 * Exemplo do header inserido:
 *   Authorization: ******
 */
class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenManager.getToken().firstOrNull() }

        val request = chain.request().newBuilder().apply {
            if (!token.isNullOrBlank()) {
                addHeader("Authorization", "Bearer " + token)
            }
        }.build()

        return chain.proceed(request)
    }
}
