package com.pluck.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.random.Random

/** Internal model for a single confetti particle. */
private data class ConfettiParticle(
    val startX: Float,
    val startY: Float,
    val velocityX: Float,
    val velocityY: Float,
    val radiusPx: Float,
    val color: Color
)

/**
 * Lightweight confetti burst
 *
 * @param trigger Increment this value to fire a new animation.
 */
@Composable
fun ConfettiBurst(
    trigger: Boolean,
    modifier: Modifier = Modifier,
    particleCount: Int = 28
) {
    val density = LocalDensity.current
    val palette = listOf(
        PluckPalette.Primary,
        PluckPalette.Secondary,
        PluckPalette.Tertiary,
        PluckPalette.Magenta,
        PluckPalette.Pink
    )
    val particles = remember(trigger, density) {
        if (!trigger) emptyList() else List(particleCount) { index ->
            val random = Random(997 + index * 23)
            val radiusPx = with(density) { (6 + random.nextInt(6)).dp.toPx() }
            ConfettiParticle(
                startX = random.nextFloat(),
                startY = random.nextFloat() * 0.2f,
                velocityX = (random.nextFloat() - 0.5f) * 0.7f,
                velocityY = 0.8f + random.nextFloat(),
                radiusPx = radiusPx,
                color = palette[random.nextInt(palette.size)]
            )
        }
    }
    val progress = remember { Animatable(0f) }
    var isActive by remember { mutableStateOf(false) }

    LaunchedEffect(trigger) {
        if (!trigger || particles.isEmpty()) return@LaunchedEffect
        isActive = true
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1100, easing = LinearOutSlowInEasing)
        )
        isActive = false
    }

    if (!isActive || particles.isEmpty()) {
        return
    }

    val progressValue = progress.value
    Canvas(modifier = modifier) {
        particles.forEach { particle ->
            val xFraction = (particle.startX + particle.velocityX * progressValue).coerceIn(0f, 1f)
            val fallProgress = progressValue * progressValue
            val yFraction = (particle.startY + particle.velocityY * fallProgress).coerceIn(0f, 1.2f)
            drawCircle(
                color = particle.color.copy(alpha = 1f - progressValue.coerceIn(0f, 0.9f)),
                radius = particle.radiusPx,
                center = Offset(
                    x = xFraction * size.width,
                    y = yFraction * size.height
                )
            )
        }
    }
}
