package com.example.lost.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MarkunreadMailbox
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.MarkUnreadChatAlt
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lost.components.messages.CategorizeChatsChipGroup
import com.example.lost.components.messages.ChatsList
import com.example.lost.data.dataclasses.ChatsCategory
import com.example.lost.navigation.MessagesRoutes
import com.example.lost.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessagesScreen(
    navHostController: NavHostController
) {
    var active by remember {
        mutableStateOf(false)
    }

    var textState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        var text by remember {
                            mutableStateOf("")
                        }

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp),
                            value = text,
                            onValueChange = {
                                text = it
                            },
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier
                                        .padding(start = 16.dp),
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Send Icon"
                                )
                            },
                            trailingIcon = {
                                if (
                                    text.isNotEmpty()
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(end = 16.dp)
                                            .clip(RoundedCornerShape(25.dp))
                                            .clickable {
                                                text = ""
                                            },
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Send Icon"
                                    )
                                }
                            },
                            placeholder = {
                                Text(text = "Szukaj...")
                            },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true
                        )
                    }

                    CategorizeChatsChipGroup(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        items = listOf(
                            ChatsCategory("Wszystkie"),
                            ChatsCategory("Nieprzeczytane", Icons.Outlined.MarkUnreadChatAlt),
                            ChatsCategory("Zakończone", Icons.Outlined.Check),
                            ChatsCategory("Archiwum", Icons.Outlined.Archive),
                            ChatsCategory("Spam", Icons.Outlined.WarningAmber)
                        )
                    ) {

                    }
                }
            }
        ) {
            ChatsList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                navHostController.navigate("${MessagesRoutes.Chat.route}/$it")
            }
        }
    }
}