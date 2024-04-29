package com.example.lost.components.popular

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lost.components.home.SwipeLostInfoCard
import com.example.lost.data.dataclasses.LostItem
import com.example.lost.screens.imagesUrls
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LikedByMeLostItems(
    modifier: Modifier = Modifier
) {
    val items = remember {
        mutableStateListOf<LostItem>()
    }

    val context = LocalContext.current

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
        ElevatedCard(
            modifier = modifier
        ) {
            if (items.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 7.5.dp)
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
}