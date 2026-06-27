package com.barbercontrol.app.core.network

/**
 * Classe selada que encapsula o resultado de operações assíncronas (API/banco).
 *
 * Uso:
 * ```
 * when (result) {
 *     is Resource.Success -> handleSuccess(result.data)
 *     is Resource.Error   -> showError(result.message)
 *     is Resource.Loading -> showLoading()
 * }
 * ```
 */
sealed class Resource<out T> {

    /** Operação em andamento. */
    data object Loading : Resource<Nothing>()

    /** Operação concluída com sucesso. */
    data class Success<T>(val data: T) : Resource<T>()

    /** Operação falhou. [message] descreve o erro; [data] pode conter dados parciais. */
    data class Error<T>(
        val message: String,
        val data: T? = null
    ) : Resource<T>()
}
