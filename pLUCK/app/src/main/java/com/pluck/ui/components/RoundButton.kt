package com.pluck.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

/**
 * A generic round button that executes `onClick` when pressed
 * @param onClick Function executed when button is pressed
 */
@Composable
fun RoundIconButton(
    onClick: () -> Unit = {},
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    circleColor: Color = PluckPalette.Surface,
    iconColor: Color = PluckPalette.Primary
) {
    Surface(
        modifier = modifier
            .size(56.dp),
        shape = CircleShape,
        color = circleColor,
        contentColor = iconColor,
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

@Composable
fun RoundedTextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    shapeColor: Color = PluckPalette.Surface,
    textColor: Color = PluckPalette.Primary,
    textSize: Int = 24,
    padding: Dp = 8.dp,
    style: TextStyle = LocalTextStyle.current
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = shapeColor,
        contentColor = textColor,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        onClick = onClick,
    )
    {
        Text(
            text = text,
            modifier = Modifier.padding(padding),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(value = textSize.toFloat(), TextUnitType.Sp),
            style = style
        )
    }
}

/**
 * A specialization of the RoundButton composable for left aligned back buttons
 */
@Composable
fun BackButton(
    onBack: () -> Unit,
    padding: Dp = 16.dp
) {
    RoundIconButton(
        onClick = onBack,
        imageVector = Icons.Outlined.ArrowBack,
        contentDescription = "Back",
        modifier = Modifier.padding(padding)
    )
}
