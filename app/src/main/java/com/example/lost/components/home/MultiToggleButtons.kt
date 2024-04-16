package com.example.lost.components.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import com.example.lost.data.dataclasses.FilterOptions

@Composable
fun MultiToggleButtons(
    currentSelection: FilterOptions,
    toggleStates: List<FilterOptions>,
    onToggleChange: (FilterOptions) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        toggleStates.forEachIndexed { _, toggleState ->
            val isSelected = currentSelection.name.lowercase() == toggleState.name.lowercase()
            val alpha by animateFloatAsState(
                targetValue = if (isSelected) 1f else 0f,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                label = ""
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.onSecondary.copy(
                            alpha = alpha
                        )
                    )
                    .toggleable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        value = isSelected,
                        enabled = true,
                        onValueChange = { selected ->
                            if (selected) {
                                onToggleChange(toggleState)
                            }
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                val tint = if (isSelected) toggleState.brush else MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.4f
                )

                val alpha = if (isSelected) 0.99f else 0.4f

                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer(
                            alpha = alpha
                        )
                        .scale(1.2f)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(tint, blendMode = BlendMode.SrcAtop)
                            }
                        },
                    imageVector = toggleState.icon,
                    contentDescription = toggleState.name
                )
            }

        }
    }
}