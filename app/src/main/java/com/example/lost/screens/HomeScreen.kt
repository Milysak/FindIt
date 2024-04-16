package com.example.lost.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.FiberNew
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.RemoveRedEye
import androidx.compose.material.icons.rounded.Timelapse
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.lost.R
import com.example.lost.components.home.MultiToggleButtons
import com.example.lost.components.home.SwipeLostInfoCard
import com.example.lost.data.dataclasses.FilterOptions
import com.example.lost.data.dataclasses.LostItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("ResourceType")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current

    val filterOptions = listOf(
        FilterOptions(
            "near",
            Icons.Rounded.LocationOn,
            "Najbliższe",
            if (isSystemInDarkTheme()) Color(0xFFFF5252) else Color(0xFFFF5252)
        ),
        FilterOptions(
            "popular",
            Icons.Rounded.RemoveRedEye,
            "Popularne",
            if (isSystemInDarkTheme()) Color(0xFF70D3FF) else Color(0xFF2979FF)
        ),
        FilterOptions(
            "liked",
            Icons.Rounded.AutoAwesome,
            "Polubione",
            if (isSystemInDarkTheme()) Color(0xFFEA80FC) else Color(0xFFD500F9)
        ),
        FilterOptions(
            "new",
            Icons.Rounded.FiberNew,
            "Najnowsze",
            if (isSystemInDarkTheme()) Color(0xFF7BFFBF) else Color(0xFF00C853)
        ),
        FilterOptions(
            "end",
            Icons.Rounded.Timelapse,
            "Najstarsze",
            if (isSystemInDarkTheme()) Color(0xFFFFB74D) else Color(0xFFFFAB00)
        ),
    )

    val imagesUrls = listOf(
        "https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/220429/pexels-photo-220429.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/1478685/pexels-photo-1478685.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/206359/pexels-photo-206359.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/312839/pexels-photo-312839.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/8498872/pexels-photo-8498872.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/20099418/pexels-photo-20099418/free-photo-of-morze-woda-ocean-zwierze.png?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        "https://images.pexels.com/photos/20174739/pexels-photo-20174739.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
    )

    var currentSelection by remember {
        mutableStateOf(filterOptions[0])
    }

    val items = remember {
        mutableStateListOf<LostItem>()
    }

    LaunchedEffect(key1 = Unit) {
        for (i in 0 until 10) {
            items.add(
                LostItem(
                    id = i,
                    name = "Doggo",
                    location = "Katowice",
                    image = imagesUrls[(i + 5) % imagesUrls.size]
                ),
            )
        }
    }

    val pageCount = 5

    val pagerState = rememberPagerState(
        pageCount = { pageCount }
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.65f),
                horizontalArrangement = Arrangement.Center
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .fillMaxHeight()
                        .padding(top = 16.dp, start = 16.dp, end = 4.dp, bottom = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.onSecondary
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(1f),
                                text = currentSelection.label.uppercase(),
                                color = MaterialTheme.colorScheme.primary,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            )

                            Icon(
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .scale(0.8f)
                                    .clickable {

                                    },
                                painter = painterResource(id = R.drawable.settings_sliders),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    if (items.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(top = 8.dp)
                        ) {
                            items(
                                items = items,
                                key = { it.id }
                            ) { item ->
                                SwipeLostInfoCard(
                                    item = item,
                                    onRemove = {
                                        items.remove(
                                            item
                                        )
                                    }
                                ) {
                                    Toast.makeText(
                                        context,
                                        "Obiekt nr. $it.id",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Lista jest\nPUSTA!",
                                color = Color(0xFFEF5350),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(top = 16.dp, start = 4.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    MultiToggleButtons(
                        currentSelection = currentSelection,
                        toggleStates = filterOptions
                    ) {
                        currentSelection = it
                    }
                }
            }

            ElevatedCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    var key by remember {
                        mutableStateOf(false)
                    }

                    var userScrollEnabled by remember {
                        mutableStateOf(true)
                    }

                    LaunchedEffect(key1 = key) {
                        launch {
                            delay(3000)

                            with(pagerState) {
                                if (!this.isScrollInProgress) {
                                    userScrollEnabled = false

                                    val target =
                                        if (currentPage < pageCount - 1) currentPage + 1 else 0

                                    animateScrollToPage(
                                        page = target,
                                        animationSpec = tween(
                                            durationMillis = 1250,
                                            easing = FastOutSlowInEasing
                                        )
                                    )
                                }

                                key = !key

                                userScrollEnabled = true
                            }
                        }
                    }

                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxHeight(0.85f)
                            .padding(12.dp),
                        state = pagerState,
                        pageSpacing = 8.dp,
                        userScrollEnabled = userScrollEnabled,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val painter = rememberAsyncImagePainter(imagesUrls[it])
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
                                .fillMaxSize()
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
                    }

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            Box(
                                modifier = Modifier
                                    .padding(start = 2.5.dp, end = 2.5.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (pagerState.currentPage == iteration)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.onBackground.copy(
                                                alpha = 0.4f
                                            )
                                    )
                                    .size(
                                        if (pagerState.currentPage == iteration) 7.dp else 5.5.dp
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingAnimation() {
    val animation = rememberInfiniteTransition(label = "")
    val progress by animation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(60.dp)
            .scale(progress)
            .alpha(1f - progress)
            .border(
                5.dp,
                color = Color.Black,
                shape = CircleShape
            )
    )
}