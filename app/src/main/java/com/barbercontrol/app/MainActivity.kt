package com.barbercontrol.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.barbercontrol.app.presentation.navigation.NavGraph
import com.barbercontrol.app.presentation.theme.BarberControlTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity principal e única do app (single-activity architecture).
 * O Jetpack Compose NavGraph gerencia toda a navegação entre telas.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BarberControlTheme {
                NavGraph()
            }
        }
    }
}
