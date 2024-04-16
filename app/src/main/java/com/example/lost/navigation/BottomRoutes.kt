package com.example.lost.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomRoutes(
    val route: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
) {
    data object Home : BottomRoutes("home_screen", Icons.Rounded.Home, Icons.Rounded.Home)
    data object Love : BottomRoutes("like_to_visit_screen", Icons.Rounded.Star, Icons.Rounded.Star)
    data object Map : BottomRoutes("map_screen", Icons.Rounded.Map, Icons.Rounded.Map)
    data object Messages : BottomRoutes("compass_screen", Icons.Rounded.Message, Icons.Rounded.Message)
    data object Settings : BottomRoutes("settings_screen", Icons.Rounded.Settings, Icons.Rounded.Settings)
}