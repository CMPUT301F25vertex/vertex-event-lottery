package com.pluck.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A generic list composable used to ensure consistent styling across different parts of the app.
 * Fills most of the screen with a rectangle with rounded corners and has scrollable content in the
 * middle.
 * @param listElements Scrollable elements
 */
@Composable
fun SquircleScrollableLazyList(
    listElements: List<ComposableItem>
) {
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    )
    {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(36.dp),
            color = PluckPalette.Surface,
            tonalElevation = 0.dp,
            shadowElevation = 16.dp
        )
        {
            LazyColumn (
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.Start
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
    }
}
