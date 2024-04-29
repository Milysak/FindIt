package com.example.lost.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.lost.mapstyles.DarkMapStyle
import com.example.lost.mapstyles.LightMapStyle
import com.example.lost.screens.HomeScreen
import com.example.lost.screens.LoadingAnimation
import com.example.lost.screens.MapScreen
import com.example.lost.screens.MessagesScreen
import com.example.lost.screens.PopularScreen
import com.example.lost.screens.SettingsScreen
import com.example.lost.screens.imagesUrls
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
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
                    modifier = modifier,
                    navHostController = navHostController
                )
            }
        }

        composable(route = BottomRoutes.Messages.route) {
            Box(
                modifier = modifier
            ) {
                MessagesScreen(
                    navHostController = navHostController
                )
            }
        }

        composable(route = BottomRoutes.Settings.route) {
            Box(
                modifier = modifier
            ) {
                SettingsScreen(
                    navHostController = navHostController
                )
            }
        }

        composable(route = "${SettingsRoutes.Account.route}/{userId}") {
            val userId = it.arguments?.getString("userId")

            Scaffold(
                topBar = {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(25.dp))
                                    .clickable {
                                        navHostController.popBackStack()
                                    },
                                imageVector = Icons.Default.ChevronLeft,
                                contentDescription = "Arrow Back"
                            )

                            Text(
                                modifier = Modifier
                                    .padding(16.dp),
                                text = userId ?: "KONTO",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Person Icon"
                            )
                        }
                    }
                }
            ) {

            }
        }

        composable(route = "${MessagesRoutes.Chat.route}/{userId}") {
            val userId = it.arguments?.getString("userId")

            Scaffold(
                topBar = {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(16.dp, 16.dp, 8.dp, 16.dp)
                                    .clip(RoundedCornerShape(25.dp))
                                    .clickable {
                                        navHostController.popBackStack()
                                    },
                                imageVector = Icons.Default.ChevronLeft,
                                contentDescription = "Arrow Back"
                            )

                            val painter = rememberAsyncImagePainter(
                                imagesUrls[0]
                            )
                            val state = painter.state

                            val transition by animateFloatAsState(
                                targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f,
                                label = ""
                            )

                            if (state is AsyncImagePainter.State.Loading) {
                                LoadingAnimation()
                            }

                            Image(
                                modifier = Modifier
                                    .alpha(transition)
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(
                                        MaterialTheme.colorScheme.onBackground.copy(
                                            alpha = 0.2f
                                        )
                                    )
                                    .border(
                                        BorderStroke(
                                            width = 2.5.dp,
                                            brush = Brush.sweepGradient(
                                                listOf(
                                                    Color(0xFF4CBB17),
                                                    Color(0xBF4CBB17),
                                                    Color.Transparent,
                                                    Color.Transparent,
                                                    Color.Transparent,
                                                    Color.Transparent,
                                                    Color.Transparent,
                                                    Color(0xBF4CBB17),
                                                    Color(0xFF4CBB17)
                                                )
                                            )
                                        ),
                                        CircleShape
                                    ),
                                painter = painter,
                                alignment = Alignment.CenterStart,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                modifier = Modifier
                                    .padding(16.dp),
                                text = userId ?: "KONTO",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                bottomBar = {
                    var text by remember {
                        mutableStateOf("")
                    }

                    Box (
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .height(55.dp),
                            value = text,
                            onValueChange = {
                                text = it
                            },
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .clip(RoundedCornerShape(25.dp))
                                        .clickable {

                                        },
                                    imageVector = Icons.Default.PhotoCamera,
                                    contentDescription = "Send Icon"
                                )
                            },
                            trailingIcon = {
                                if (text.isNotEmpty()) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(end = 16.dp)
                                            .clip(RoundedCornerShape(25.dp))
                                            .clickable {

                                            },
                                        imageVector = Icons.Default.Send,
                                        contentDescription = "Send Icon"
                                    )
                                } else {
                                    Row {
                                        Icon(
                                            modifier = Modifier
                                                .padding(end = 8.dp)
                                                .clip(RoundedCornerShape(25.dp))
                                                .clickable {

                                                },
                                            imageVector = Icons.Default.Mic,
                                            contentDescription = "Mic Icon"
                                        )
                                        Icon(
                                            modifier = Modifier
                                                .padding(end = 16.dp)
                                                .clip(RoundedCornerShape(25.dp))
                                                .clickable {

                                                },
                                            imageVector = Icons.Default.Image,
                                            contentDescription = "Image Icon"
                                        )
                                    }
                                }
                            },
                            placeholder = {
                                Text(text = "Wiadomość...")
                            },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true
                        )
                    }
                }
            ) { innerPadding ->
                val messages = listOf(
                    "Siema", "Hejka", "Co tam słychać?", "Nudno, a tam?",
                    "Tak samo, w pracy jestem. :<", "Współczuję...", "Masz wolne?",
                    "takkk!", "Zazzzdro fest...", "No ale nie mam co robić... :<",
                    "Mozemy wyjsc gdzies po pracy", "świetny pomysł", "rynek wieczorem?",
                    "Może być!", "19:30 na rogu?", "W porządku!!!", "Ok, to do zobaczenia",
                    "Do zobaczenia", "Papa!", "Hejka!!", "<3", ":*", "Siema", "Hejka", "Co tam słychać?", "Nudno, a tam?",
                    "Tak samo, w pracy jestem. :<", "Współczuję...", "Masz wolne?",
                    "takkk!", "Zazzzdro fest...", "No ale nie mam co robić... :<",
                    "Mozemy wyjsc gdzies po pracy", "świetny pomysł", "rynek wieczorem?",
                    "Może być!", "19:30 na rogu?", "W porządku!!!", "Ok, to do zobaczenia",
                    "Do zobaczenia", "Papa!", "Hejka!!", "<3", ":*"
                )

                val chatListState = rememberLazyListState()
                
                LaunchedEffect(key1 = Unit) {
                    chatListState.scrollToItem(chatListState.layoutInfo.totalItemsCount)
                }

                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp),
                    state = chatListState
                ) {
                    itemsIndexed(
                        items = messages, itemContent = { index, message ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = if (index % 2 == 0) Alignment.CenterStart else Alignment.CenterEnd,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = 3.dp)
                                        .clip(RoundedCornerShape(25.dp))
                                        .background(
                                            if (index % 2 == 0)
                                                MaterialTheme.colorScheme.onSecondary
                                            else
                                                MaterialTheme.colorScheme.onPrimary
                                        )
                                        .padding(horizontal = 16.dp)
                                        .padding(vertical = 8.dp),
                                    text = message,
                                )
                            }
                        }
                    )
                }
            }
        }

        composable(route = MapRoutes.AddLostItem.route) {
            var dialogActive by remember {
                mutableStateOf(false)
            }

            Scaffold(
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                    ) {
                        OutlinedButton(
                            modifier = Modifier
                                .weight(1f),
                            onClick = {
                                dialogActive = true
                            }
                        ) {
                            Text(text = "Anuluj")
                        }
                        
                        Spacer(modifier = Modifier.width(10.dp))

                        Button(
                            modifier = Modifier
                                .weight(1f),
                            onClick = {
                                /* TODO */
                            }
                        ) {
                            Text(text = "Zgłoś")
                        }
                    }
                }
            ) {
                AnimatedVisibility(
                    visible = dialogActive
                ) {
                    AlertDialog(
                        onDismissRequest = {
                            /* Nothing */
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dialogActive = false
                                    navHostController.popBackStack()
                                }
                            ) {
                                Text(
                                    text = "Tak",
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    dialogActive = false
                                }
                            ) {
                                Text(
                                    text = "Nie"
                                )
                            }
                        },
                        title = {
                            Text(
                                text = "Anuluj zgłoszenie"
                            )
                        },
                        text = {
                            Text(
                                text = "Czy na pewno chcesz zrezygnować z utworzonego przed chwilą zgłoszenia?"
                            )
                        }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    var properties by mutableStateOf(
                        MapProperties(
                            minZoomPreference = 3.25F,
                        )
                    )

                    val cameraPositionState = rememberCameraPositionState()

                    var value by remember {
                        mutableFloatStateOf(50f)
                    }

                    properties = if (isSystemInDarkTheme()) {
                        properties.copy(
                            mapStyleOptions = MapStyleOptions(DarkMapStyle.json)
                        )
                    } else {
                        properties.copy(
                            mapStyleOptions = MapStyleOptions(LightMapStyle.json)
                        )
                    }

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.25f)
                            .clip(RoundedCornerShape(25.dp)),
                    ) {
                        GoogleMap(
                            modifier = Modifier
                                .fillMaxSize(),
                            cameraPositionState = cameraPositionState,
                            properties = properties,
                            uiSettings = MapUiSettings(
                                zoomControlsEnabled = false,
                                zoomGesturesEnabled = false,
                                mapToolbarEnabled = false,
                                rotationGesturesEnabled = false,
                                scrollGesturesEnabled = false,
                                scrollGesturesEnabledDuringRotateOrZoom = false,
                                tiltGesturesEnabled = false
                            ),
                            onMapLongClick = {
                                /* TODO */
                            },
                            onMapClick = {
                                /* TODO */
                            }
                        ) {
                            Circle(
                                center = LatLng(0.0, 0.0),
                                radius = value.toInt().toDouble()*500,
                                strokeColor = MaterialTheme.colorScheme.primary,
                                fillColor = MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.25f
                                )
                            )
                        }
                    }

                    Slider(
                        value = value,
                        onValueChange = { value = it },
                        valueRange = 50f..2000f
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "${value.toInt()}m",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    var name by remember {
                        mutableStateOf("")
                    }
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = name,
                        onValueChange = { name = it },
                        placeholder = {
                            Text(text = "Nazwa...")
                        },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    var description by remember {
                        mutableStateOf("")
                    }
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        value = description,
                        onValueChange = { description = it },
                        placeholder = {
                            Text(text = "Opis...")
                        },
                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        maxLines = 5
                    )
                }
            }
        }
    }
}