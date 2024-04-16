package com.example.lost.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lost.components.map.FloatingActionButtonMenu
import com.example.lost.components.map.TopBarMenu
import com.example.lost.mapstyles.DarkMapStyle
import com.example.lost.mapstyles.LightMapStyle
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MapScreen(
    modifier: Modifier = Modifier
) {
    var properties by mutableStateOf(
        MapProperties(
            minZoomPreference = 3.25F
        )
    )

    properties = if (isSystemInDarkTheme()) {
        properties.copy(
            mapStyleOptions = MapStyleOptions(DarkMapStyle.json)
        )
    } else {
        properties.copy(
            mapStyleOptions = MapStyleOptions(LightMapStyle.json)
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButtonMenu(
                modifier = modifier
            )
        },
        topBar = {
            TopBarMenu()
        }
    ) {
        val cameraPositionState = rememberCameraPositionState()

        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = MapUiSettings(zoomControlsEnabled = false, mapToolbarEnabled = false),
            onMapLongClick = {
                /* TODO */
            },
            onMapClick = {
                /* TODO */
            }
        ) {
        }
    }
}