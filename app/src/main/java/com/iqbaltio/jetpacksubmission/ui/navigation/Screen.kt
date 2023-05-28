package com.iqbaltio.jetpacksubmission.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailGame : Screen("home/{gameId}") {
        fun createRoute(gameId: Long) = "home/$gameId"
    }
}
