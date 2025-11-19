package com.pluck.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.pluck.ui.theme.PluckTheme
import kotlinx.coroutines.delay
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
 *      always be visible, set to -1 to have no persistent element
 * @param bottomBar The dynamically hiding bottom bar visible to the user
 * @param additionalContent Additional content to be added after scrollable elements
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AutoHidingBarScroller(
    listElements: List<ComposableItem>,
    indexOfPersistentElement: Int = -1,
    bottomBar: (@Composable () -> Unit)? = null,
    spacingBetweenItems: Dp = 16.dp,
    onRefresh: (() -> Unit)? = null,
    isRefreshing: Boolean = false,
    additionalContent: @Composable () -> Unit = { },
) {
    // Must be a valid index
    assert(indexOfPersistentElement < listElements.size)

    val listState = rememberLazyListState()
    val bottomBarState = rememberBottomAppBarState()

    var bottomBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(
        state = bottomBarState
    )
    if (!listState.canScrollBackward) {
        bottomBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(canScroll = { false })
    }

    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold(
        modifier = Modifier
            .nestedScroll(bottomBarScrollBehavior.nestedScrollConnection),
        bottomBar = {
            if (bottomBar != null) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    scrollBehavior = bottomBarScrollBehavior,
                    containerColor = PluckPalette.Surface
                ) {
                    bottomBar()
                }
            }
        }
    )
    { paddingValues  ->

        PluckLayeredBackground(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier.pullToRefresh(
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh ?: { },
                    state = pullToRefreshState,
                    enabled = onRefresh != null
                )
            )
            {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = paddingValues.calculateBottomPadding(),
                        )
                )
                {
                    item {
                        Spacer(modifier = Modifier.height(spacingBetweenItems))
                    }

                    for (i in listElements.indices) {
                        if (i != indexOfPersistentElement) {
                            item {
                                Box(
                                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                                ) {
                                    listElements[i].content()
                                }
                            }

                            if (i != indexOfPersistentElement - 1) {
                                item {
                                    Spacer(modifier = Modifier.height(spacingBetweenItems))
                                }
                            }

                        } else {
                            item {
                                Spacer(modifier = Modifier.height(spacingBetweenItems / 2))
                            }

                            stickyHeader {
                                Surface(
                                    color = PluckPalette.Secondary,
                                    shape = RoundedCornerShape(36.dp),
                                    modifier = Modifier.padding(spacingBetweenItems / 2)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(spacingBetweenItems / 2)
                                    ) {
                                        listElements[indexOfPersistentElement].content()
                                    }
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.height(spacingBetweenItems / 2))
                            }
                        }
                    }
                }

                PullToRefreshDefaults.Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    state = pullToRefreshState,
                    containerColor = PluckPalette.Surface,
                    color = PluckPalette.Primary
                )
            }
        }

        additionalContent()
    }
}
