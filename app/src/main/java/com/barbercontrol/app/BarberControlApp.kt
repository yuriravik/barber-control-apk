package com.barbercontrol.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Classe de aplicação principal.
 * A anotação @HiltAndroidApp inicializa o grafo de injeção de dependências do Hilt.
 */
@HiltAndroidApp
class BarberControlApp : Application()
