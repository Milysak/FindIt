package com.example.lost.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lost.screens.HomeScreen
import com.example.lost.screens.MapScreen
import com.example.lost.screens.MessagesScreen
import com.example.lost.screens.PopularScreen
import com.example.lost.screens.SettingsScreen

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = BottomRoutes.Home.route
    ) {
        composable(route = BottomRoutes.Home.route) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                HomeScreen()
            }
        }

        composable(route = BottomRoutes.Love.route) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                PopularScreen()
            }
        }

        composable(route = BottomRoutes.Map.route) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MapScreen(
                    modifier = modifier
                )
            }
        }

        composable(route = BottomRoutes.Messages.route) {
            Box(
                modifier = modifier
            ) {
                MessagesScreen()
            }
        }

        composable(route = BottomRoutes.Settings.route) {
            Box(
                modifier = modifier
            ) {
                SettingsScreen()
            }
        }
    }
}