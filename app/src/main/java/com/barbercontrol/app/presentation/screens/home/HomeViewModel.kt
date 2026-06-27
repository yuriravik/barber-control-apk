package com.barbercontrol.app.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barbercontrol.app.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Evento de navegação emitido pela HomeScreen. */
sealed class HomeEvent {
    data object NavigateToLogin : HomeEvent()
}

/**
 * ViewModel da tela Home (placeholder).
 *
 * Responsável atualmente apenas pelo logout.
 * Expanda para carregar dados do dashboard conforme o backend evoluir.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _event = MutableSharedFlow<HomeEvent>()
    val event: SharedFlow<HomeEvent> = _event.asSharedFlow()

    fun onLogoutClick() {
        viewModelScope.launch {
            authRepository.logout()
            _event.emit(HomeEvent.NavigateToLogin)
        }
    }
}
