package com.example.lost.components.messages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.lost.screens.LoadingAnimation
import com.example.lost.screens.imagesUrls

@Composable
fun ChatBar(
    id: Int = 0,
    name: String = "Noname",
    onClick: () -> Unit
) {
    Column {
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            color = DividerDefaults.color.copy(
                alpha = 0.25f
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .padding(horizontal = 20.dp)
                .padding(vertical = 10.dp),
        ) {
            val painter = rememberAsyncImagePainter(
                imagesUrls[id % imagesUrls.size]
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
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.2f
                        )
                    )
                    .border(
                        BorderStroke(
                            width =  if(id % 3 == 0) 2.5.dp else 0.dp,
                            brush = if(id % 3 == 0) Brush.sweepGradient(
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
                            ) else
                            Brush.sweepGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Transparent
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

            Column(
                modifier = Modifier
                    .padding(start = 15.dp),
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Wiadomość od powyższej osoby...",
                    fontSize = 14.sp
                )
            }
        }
    }
}