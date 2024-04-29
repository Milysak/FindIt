package com.example.lost.components.messages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lost.data.dataclasses.ChatsCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorizeChatsChipGroup(
    modifier: Modifier = Modifier,
    items: List<ChatsCategory>,
    defaultSelectedItemIndex: Int = 0,
    onSelectedChanged: (Int) -> Unit = {}
) {
    var selectedItemIndex by remember { mutableIntStateOf(defaultSelectedItemIndex) }

    LazyRow(
        modifier = modifier
    ) {
        items(items.size) { index: Int ->
            FilterChip(
                modifier = Modifier.padding(end = 6.dp),
                selected = items[selectedItemIndex] == items[index],
                onClick = {
                    selectedItemIndex = index
                    onSelectedChanged(index)
                },
                label = { Text(items[index].name) },
                leadingIcon = if (items[selectedItemIndex] == items[index]) {
                    {
                        items[index].icon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = "Localized Description",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    }
                } else {
                    {

                    }
                }
            )
        }
    }
}