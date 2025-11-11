package com.pluck.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pluck.ui.theme.PluckTheme
import kotlin.math.roundToInt

/**
 * A generic full screen composable that shows the user a list of items (listElements), however
 * one item can be set as `persistent`, that item will always be visible as you scroll, any elements
 * above it will be automatically hidden as you scroll below a certain point.
 *
 * The composable also requires a bottom bar that will be hidden while scrolling down the list, but
 * can be easily retrieved when the user starts to scroll up again.
 *
 * @param listElements The elements you wish to show the user
 * @param indexOfPersistentElement An index inside the listElements list of the element you wish to
 *      always be visible
 * @param bottomBar The dynamically hiding bottom bar visible to the user
 * @param additionalContent Additional content to be added after scrollable elements
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoHidingBarScroller(
    listElements: List<ComposableItem>,
    indexOfPersistentElement: Int,
    bottomBar: @Composable () -> Unit,
    additionalContent: @Composable () -> Unit
) {
    // Must be a valid index
    assert(indexOfPersistentElement < listElements.size)

    val scrollableElements = listElements.size - (indexOfPersistentElement + 1)

    val listState = rememberLazyListState()

    var bottomBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    if (!listState.canScrollBackward) {
        bottomBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(canScroll = { false })
    }

    val overviewCollapsed by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        modifier = Modifier
            .nestedScroll(bottomBarScrollBehavior.nestedScrollConnection),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                scrollBehavior = bottomBarScrollBehavior,
                containerColor = PluckPalette.Surface
            ) {
                bottomBar()
            }
        }
    )
    { paddingValues  ->
        PluckLayeredBackground(modifier = Modifier.fillMaxSize()) {
            if (scrollableElements < 2) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = paddingValues.calculateBottomPadding(),
                            start = 16.dp,
                            end = 16.dp
                        ),
                )
                {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    listElements.forEach { listElement ->
                        item {
                            listElement.content()
                        }

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 16.dp,
                            bottom = paddingValues.calculateBottomPadding(),
                            start = 16.dp,
                            end = 16.dp
                        ),
                )
                {
                    AnimatedVisibility(visible = !overviewCollapsed) {
                        Column {
                            for (i in listElements.indices) {
                                if (i < indexOfPersistentElement) {
                                    listElements[i].content()

                                    Spacer(modifier = Modifier.size(16.dp))
                                }
                            }
                        }
                    }

                    listElements[indexOfPersistentElement].content()

                    if (overviewCollapsed) {
                        Spacer(modifier = Modifier.size(16.dp))
                    }

                    LazyColumn(state = listState)
                    {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        for (i in listElements.indices) {
                            if (i > indexOfPersistentElement) {
                                item {
                                    listElements[i].content()
                                }

                                item {
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                        }
                    }
                }
            }

            additionalContent()
        }
    }
}
