package com.example.lost.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Houseboat
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.lost.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1250
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(750)
        navController.popBackStack()
        navController.navigate(Routes.Main.route)
    }

    SplashScreen(alpha = alphaAnimation.value)
}

@Composable
fun SplashScreen(alpha: Float) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            MaterialTheme.colorScheme.background
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .scale(2f),
            imageVector = Icons.Rounded.Houseboat,
            contentDescription = null
        )
    }
}

@Composable
@Preview
fun SplashPreview() {
    SplashScreen(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashPreviewDarkMode() {
    SplashScreen(alpha = 1f)
}