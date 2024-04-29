package com.example.lost.components.map

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FloatingActionButtonMenu(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            onClick()
        },
        containerColor = MaterialTheme.colorScheme.onSecondary,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add Lost Button"
        )
    }
}