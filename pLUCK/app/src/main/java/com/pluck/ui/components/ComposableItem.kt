package com.pluck.ui.components

import androidx.compose.runtime.Composable

/**
 * A generic wrapper for a composable function
 */
data class ComposableItem(
    val content: @Composable () -> Unit
)
