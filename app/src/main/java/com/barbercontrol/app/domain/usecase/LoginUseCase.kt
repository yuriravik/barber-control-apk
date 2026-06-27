package com.barbercontrol.app.domain.usecase

import com.barbercontrol.app.core.network.Resource
import com.barbercontrol.app.data.repository.AuthRepository
import com.barbercontrol.app.domain.model.User
import javax.inject.Inject

/**
 * Caso de uso que encapsula a lógica de negócio do login.
 *
 * Valida os campos antes de delegar ao repositório.
 */
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    /**
     * Executa o login após validar os parâmetros de entrada.
     *
     * @param email E-mail do usuário.
     * @param password Senha do usuário.
     * @return [Resource] com o resultado da operação.
     */
    suspend operator fun invoke(email: String, password: String): Resource<User> {
        if (email.isBlank()) {
            return Resource.Error("O e-mail não pode estar vazio.")
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Resource.Error("Informe um e-mail válido.")
        }
        if (password.isBlank()) {
            return Resource.Error("A senha não pode estar vazia.")
        }
        if (password.length < 6) {
            return Resource.Error("A senha deve ter pelo menos 6 caracteres.")
        }
        return authRepository.login(email, password)
    }
}
