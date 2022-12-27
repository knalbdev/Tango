package com.setiadev.tango.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Team: Screen("team")
    object Profile: Screen("profile")
    object DetailPlayer: Screen("home/{playerId}") {
        fun createRoute(playerId: Long) = "home/$playerId"
    }
}
