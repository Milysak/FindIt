package com.example.lost.components.messages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val list = listOf(
    "Amanda", "Beata", "Cycylia", "Darya", "Oliwia", "Justi", "Miłosz",
    "Katarzyna", "Ewa", "Atena", "Arthur", "Jacob", "Amanda", "Beata",
    "Cycylia", "Darya", "Oliwia", "Justi", "Miłosz",
    "Katarzyna", "Ewa", "Atena", "Arthur", "Jacob",
)
@Composable
fun ChatsList(
    modifier: Modifier = Modifier,
    onChatClick: (String) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {
            items(
                items = list
            ) {
                ChatBar(
                    list.indexOf(it),
                    it
                ) {
                    onChatClick(it)
                }
            }
        }
    }
}