package com.example.lost.navigation

sealed class Routes(val route: String) {
    data object Splash : Routes("splash_screen")
    data object Main : Routes("main_screen")
}