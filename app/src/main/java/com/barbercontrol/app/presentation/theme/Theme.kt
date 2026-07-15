package com.barbercontrol.app.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

/**
 * Esquema de cores escuro do Barber Control.
 * O app usa somente dark theme para manter a identidade visual.
 */
private val DarkColorScheme = darkColorScheme(
    primary = AccentAmber,
    onPrimary = PrimaryDark,
    primaryContainer = SecondaryDark,
    onPrimaryContainer = OnSurfaceDark,
    secondary = GoldAccent,
    onSecondary = PrimaryDark,
    background = PrimaryDark,
    onBackground = OnSurfaceDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceDark,
    error = ErrorRed,
    onError = PrimaryDark
)

/**
 * Tema principal do Barber Control.
 *
 * Envolve toda a árvore de composables com o MaterialTheme configurado.
 */
@Composable
fun BarberControlTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = BarberControlTypography,
        content = content
    )
}
