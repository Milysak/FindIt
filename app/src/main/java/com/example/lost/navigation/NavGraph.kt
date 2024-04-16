package com.example.lost.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lost.screens.AnimatedSplashScreen
import com.example.lost.screens.BottomNavigation

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.Splash.route) {
        composable(route = Routes.Splash.route) {
            AnimatedSplashScreen(navHostController)
        }

        composable(route = Routes.Main.route) {
            BottomNavigation()
        }
    }
}