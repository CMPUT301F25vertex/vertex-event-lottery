package com.pluck.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * A generic round button that executes `onClick` when pressed
 * @param onClick Function executed when button is pressed
 */
@Composable
fun RoundButton(
    onClick: () -> Unit = {},
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            .size(56.dp),
        shape = CircleShape,
        color = PluckPalette.Surface,
        contentColor = PluckPalette.Primary,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        onClick = onClick
    )
    {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
