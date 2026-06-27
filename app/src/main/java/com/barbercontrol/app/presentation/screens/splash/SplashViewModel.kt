package com.barbercontrol.app.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbercontrol.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Evento de navegação emitido pela SplashScreen. */
sealed class SplashEvent {
    data object NavigateToLogin : SplashEvent()
    data object NavigateToHome  : SplashEvent()
}

/**
 * ViewModel da SplashScreen.
 *
 * Verifica se existe um token salvo e emite o evento de navegação correto
 * após um breve delay (para exibir o splash animado).
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _event = MutableSharedFlow<SplashEvent>()
    val event: SharedFlow<SplashEvent> = _event.asSharedFlow()

    init {
        checkAuthState()
    }

    private fun checkAuthState() {
        viewModelScope.launch {
            delay(1_500L) // Tempo mínimo de exibição do splash (ms)
            val isLoggedIn = authRepository.isLoggedIn().firstOrNull() ?: false
            if (isLoggedIn) {
                _event.emit(SplashEvent.NavigateToHome)
            } else {
                _event.emit(SplashEvent.NavigateToLogin)
            }
        }
    }
}
