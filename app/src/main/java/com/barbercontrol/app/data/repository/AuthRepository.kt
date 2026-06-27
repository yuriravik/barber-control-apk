package com.barbercontrol.app.data.repository

import com.barbercontrol.app.core.network.Resource
import com.barbercontrol.app.core.security.TokenManager
import com.barbercontrol.app.data.remote.ApiService
import com.barbercontrol.app.data.remote.dto.LoginRequestDto
import com.barbercontrol.app.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositório responsável pelas operações de autenticação.
 *
 * Faz a ponte entre a camada de dados (API/DataStore) e a camada de domínio.
 */
@Singleton
class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) {

    /** Verifica se existe um token válido armazenado. */
    fun isLoggedIn(): Flow<Boolean> = flow {
        val token = tokenManager.getToken().firstOrNull()
        emit(!token.isNullOrBlank())
    }

    /**
     * Realiza o login na API e persiste o token.
     *
     * @return [Resource.Success] com dados do usuário ou [Resource.Error] com a mensagem.
     */
    suspend fun login(email: String, password: String): Resource<User> {
        return try {
            val response = apiService.login(LoginRequestDto(email, password))
            tokenManager.saveToken(response.accessToken)
            Resource.Success(User(email = email))
        } catch (e: HttpException) {
            val message = when (e.code()) {
                401 -> "E-mail ou senha inválidos."
                422 -> "Dados inválidos. Verifique e tente novamente."
                500 -> "Erro interno no servidor. Tente mais tarde."
                else -> "Erro ${e.code()}: ${e.message()}"
            }
            Resource.Error(message)
        } catch (e: IOException) {
            Resource.Error("Sem conexão com a internet. Verifique sua rede.")
        } catch (e: Exception) {
            Resource.Error("Erro inesperado: ${e.localizedMessage ?: "desconhecido"}")
        }
    }

    /** Realiza o logout removendo o token e limpando dados locais. */
    suspend fun logout() {
        tokenManager.clearToken()
    }
}
