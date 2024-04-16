package com.example.lost.components.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.lost.R
import com.example.lost.data.dataclasses.LostItem
import com.example.lost.screens.LoadingAnimation
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeLostInfoCard(
    item: LostItem,
    onRemove: (item: LostItem) -> Unit,
    onItemClicked: (item: LostItem) -> Unit
) {
    var show by remember {
        mutableStateOf(true)
    }

    var isReset by remember {
        mutableStateOf(true)
    }

    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                true
            } else false
        }, positionalThreshold = { 150.dp.toPx() }
    )

    val alpha by animateFloatAsState(
        targetValue = if (
            dismissState.isDismissed(DismissDirection.EndToStart)
            ||
            dismissState.isDismissed(DismissDirection.StartToEnd)
        )
            1f
        else
            0f,
        animationSpec = tween(
            durationMillis = 100,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    val animationDuration = 500

    AnimatedVisibility(
        visible = show,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = dismissState,
            directions = setOf(
                DismissDirection.EndToStart,
                DismissDirection.StartToEnd
            ),
            background = {
                /*val color by animateColorAsState(
                    when (dismissState.dismissDirection) {
                        DismissDirection.EndToStart -> if (isSystemInDarkTheme()) Color(0xFFFF5252) else Color(
                            0xFFFF5252
                        )

                        *//*DismissDirection.StartToEnd -> Color.Green*//*
                        else -> MaterialTheme.colorScheme.onSecondary
                    }, label = ""
                )*/
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 4.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            when (dismissState.dismissDirection) {
                                DismissDirection.EndToStart -> Color(0xFFFF5252)
                                DismissDirection.StartToEnd -> Color(0xFF00E676)
                                else -> MaterialTheme.colorScheme.onSecondary
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (dismissState.dismissDirection == DismissDirection.EndToStart) {
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .alpha(
                                    alpha
                                ),
                            text = "Usunąć?",
                            color = Color(0xFF861414),
                            fontWeight = FontWeight.Bold
                        )

                        Row {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clickable {
                                        isReset = !isReset
                                    }
                                    .alpha(
                                        alpha
                                    ),
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh",
                                tint = Color(0xFF861414)
                            )

                            Icon(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clickable {
                                        if (dismissState.isDismissed(DismissDirection.EndToStart))
                                            show = false
                                    },
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color(0xFF861414)
                            )
                        }
                    } else if (dismissState.dismissDirection == DismissDirection.StartToEnd) {
                        Row {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .clickable {
                                        /* TODO */
                                    },
                                imageVector = Icons.Rounded.Star,
                                contentDescription = "ThumbUp",
                                tint = Color(0xFF026434)
                            )

                            Icon(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .clickable {
                                        isReset = !isReset
                                    }
                                    .alpha(
                                        alpha
                                    ),
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh",
                                tint = Color(0xFF026434)
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .alpha(
                                    alpha
                                ),
                            text = "Polubić?",
                            color = Color(0xFF026434),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            },
            dismissContent = {
                LostInfoCard(
                    item = item,
                    onItemClicked = {
                        onItemClicked(it)
                    }
                )
            }
        )
    }

    LaunchedEffect(show) {
        if (!show) {
            delay(animationDuration.toLong())
            onRemove(item)
        }
    }

    LaunchedEffect(isReset) {
        dismissState.reset()
    }

    LaunchedEffect(
        key1 = dismissState.isDismissed(DismissDirection.EndToStart),
        key2 = dismissState.isDismissed(DismissDirection.StartToEnd)
    ) {
        delay(5000)
        dismissState.reset()
    }
}

@Composable
fun LostInfoCard(
    item: LostItem,
    onItemClicked: (item: LostItem) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                onClick = {
                    onItemClicked(item)
                }
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val painter = rememberAsyncImagePainter(item.image)
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
                    .size(75.dp, 75.dp)
                    .alpha(transition)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.2f
                        )
                    ),
                painter = painter,
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {

                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp, 16.dp),
                        tint = Color(0xFFE53935)
                    )

                    Text(
                        text = item.location,
                        modifier = Modifier.padding(4.dp, 6.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 12.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                //
            }
        }
    }
}