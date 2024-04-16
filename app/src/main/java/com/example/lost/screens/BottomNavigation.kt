package com.example.lost.screens

import android.graphics.BlurMaskFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lost.navigation.BottomNavGraph
import com.example.lost.navigation.BottomRoutes

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun BottomNavigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BottomNavGraph(
                modifier = Modifier
                    .padding(innerPadding),
                navHostController = navController
            )
        }

    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        BottomRoutes.Home,
        BottomRoutes.Love,
        BottomRoutes.Map,
        BottomRoutes.Messages,
        BottomRoutes.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    val state = rememberScrollState()

    Box(
        modifier = Modifier
            .padding(start = 0.dp, end = 0.dp, top = 5.dp, bottom = 0.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp
                )
            )
            .height(65.dp),
    ) {
        Row(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.onSecondary
                )
                .fillMaxSize()
                .horizontalScroll(state, true),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true

                val contentColor by animateColorAsState(
                    targetValue = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.4f
                    ),
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                    label = ""
                )

                Box(
                    modifier = Modifier
                        .height(55.dp)
                        .clip(CircleShape)
                        .clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (currentDestination?.hierarchy?.any { it.route == screen.route } == false) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id)
                                        launchSingleTop = true
                                    }
                                }
                            }
                        ),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 14.dp, bottom = 0.dp)
                            .scale(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Icon(
                            imageVector = if (selected) screen.filledIcon else screen.outlinedIcon,
                            contentDescription = "icon",
                            tint = if (selected) contentColor else contentColor,
                            modifier = Modifier
                                .scale(1.1f),
                        )
                        
                        Spacer(
                            modifier = Modifier
                                .height(5.dp)
                        )

                        AnimatedVisibility(
                            visible = selected,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically(),
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(
                                        MaterialTheme.colorScheme.primary
                                    )
                                    .size(5.dp)
                            ) {

                            }
                        }
                    }
                }
            }
        }
    }
}

fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)