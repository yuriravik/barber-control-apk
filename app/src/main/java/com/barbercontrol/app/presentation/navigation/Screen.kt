package com.barbercontrol.app.presentation.navigation

/**
 * Rotas de navegação do app.
 *
 * Use a propriedade [route] como identificador no NavController.
 */
sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login  : Screen("login")
    data object Home   : Screen("home")
    // Adicione novas telas aqui conforme o app crescer:
    // data object Appointments : Screen("appointments")
    // data object Profile      : Screen("profile")
    // data object ServiceDetail : Screen("service/{serviceId}") {
    //     fun createRoute(serviceId: String) = "service/$serviceId"
    // }
}
