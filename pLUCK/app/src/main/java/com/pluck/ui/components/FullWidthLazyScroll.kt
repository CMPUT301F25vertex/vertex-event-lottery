package com.pluck.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ComposableItem(
    val content: @Composable () -> Unit
)

/**
 * A full width set of scrollable elements, that uses LazyColumn for improved performance on longer
 * lists
 * @param listElements Scrollable elements
 */
@Composable
fun FullWidthLazyScroll(
    listElements: List<ComposableItem>
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        listElements.forEach { listElement ->
            item{
                listElement.content()
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
