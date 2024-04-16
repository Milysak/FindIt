package com.example.lost.components.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBarMenu() {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 15.dp)
    ) {

        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .width(70.dp)
                .height(45.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Add New Thing"
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .width(70.dp)
                .height(45.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = "Add New Thing"
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .width(70.dp)
                .height(45.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "Add New Thing"
            )
        }
    }
}