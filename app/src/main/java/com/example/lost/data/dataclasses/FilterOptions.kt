package com.example.lost.data.dataclasses

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class FilterOptions(
    val name: String,
    val icon: ImageVector,
    val label: String,
    val brush: Color
)
