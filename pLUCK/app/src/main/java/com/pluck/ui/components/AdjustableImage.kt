package com.pluck.ui.components

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import coil3.compose.AsyncImage

/**
 * An image component that allows vertical repositioning within its frame.
 *
 * @param imageUrl The URL of the image to display
 * @param contentDescription Accessibility description for the image
 * @param initialOffsetY Initial vertical offset as a fraction (-1f to 1f)
 * @param onOffsetChanged Callback when the user adjusts the image position
 * @param adjustable Whether the image can be repositioned via drag gestures
 * @param modifier Modifier for the container
 * @param contentScale How the image should be scaled (default: Crop)
 */
@Composable
fun AdjustableImage(
    imageUrl: String,
    contentDescription: String?,
    initialOffsetY: Float = 0f,
    onOffsetChanged: ((Float) -> Unit)? = null,
    onOffsetCommitted: ((Float) -> Unit)? = null,
    adjustable: Boolean = true,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    var offsetY by remember(imageUrl) { mutableFloatStateOf(initialOffsetY.coerceIn(-1f, 1f)) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier
            .clipToBounds()
            .onGloballyPositioned { coordinates ->
                containerSize = coordinates.size
            }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    // Translate the image vertically based on offset
                    // offsetY ranges from -1 (top) to 1 (bottom)
                    if (containerSize.height > 0) {
                        translationY = offsetY * (containerSize.height.toFloat() * 0.5f)
                    }
                }
                .then(
                    if (adjustable) {
                        Modifier.pointerInput(Unit) {
                            detectVerticalDragGestures(
                                onVerticalDrag = { change, dragAmount ->
                                    if (containerSize.height > 0) {
                                        change.consume()
                                        val delta = dragAmount / containerSize.height.toFloat()
                                        val newOffset = offsetY + delta
                                        offsetY = newOffset.coerceIn(-1f, 1f)
                                        onOffsetChanged?.invoke(offsetY)
                                    }
                                },
                                onDragEnd = {
                                    onOffsetCommitted?.invoke(offsetY)
                                }
                            )
                        }
                    } else {
                        Modifier
                    }
                )
        )
    }
}

/**
 * An image component specifically for circular profile photos with adjustable positioning.
 *
 * @param imageUrl The URL of the profile image
 * @param contentDescription Accessibility description
 * @param initialOffsetY Initial vertical offset as a fraction (-1f to 1f)
 * @param onOffsetChanged Callback when the user adjusts the image position
 * @param adjustable Whether the image can be repositioned
 * @param modifier Modifier for the container
 */
@Composable
fun AdjustableProfileImage(
    imageUrl: String,
    contentDescription: String?,
    initialOffsetY: Float = 0f,
    onOffsetChanged: ((Float) -> Unit)? = null,
    adjustable: Boolean = true,
    modifier: Modifier = Modifier
) {
    AdjustableImage(
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        initialOffsetY = initialOffsetY,
        onOffsetChanged = onOffsetChanged,
        adjustable = adjustable,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
