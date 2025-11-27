package com.pluck.ui.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder

@Composable
fun ProfileCircle(
    /**
     * User's display name used for the textual fallback when no image is available.
     */
    userName: String,
    /**
     * Cloudinary URL for the current profile photo.
     */
    profileImageUrl: String?,
    /**
     * Whether an image upload is in progress to display the loading indicator.
     */
    isUploading: Boolean,
    /**
     * Callback invoked when the user taps the avatar or change photo action.
     */
    onChangePhoto: (() -> Unit)? = null,
    size: Int = 140,
    modifier: Modifier = Modifier.fillMaxWidth(),
    textStyle: TextStyle = MaterialTheme.typography.titleLarge
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.size(size.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = CircleShape,
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 10.dp,
                onClick = onChangePhoto ?: { },
                enabled = !isUploading
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (!profileImageUrl.isNullOrBlank()) {
//                        val imageLoader = ImageLoader.Builder(LocalContext.current)
//                            .components {
//                                if (SDK_INT >= 28) {
//                                    add(AnimatedImageDecoder.Factory())
//                                } else {
//                                    add(GifDecoder.Factory())
//                                }
//                            }
//                            .build()
                        AsyncImage(
                            model = profileImageUrl,
                            contentDescription = "Profile photo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text(
                            text = userName.trim()
                                .takeIf { it.isNotEmpty() }
                                ?.first()
                                ?.uppercaseChar()
                                ?.toString()
                                ?: "?",
                            style = textStyle.copy(
                                color = PluckPalette.Primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    if (isUploading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(PluckPalette.Primary.copy(alpha = 0.45f)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = PluckPalette.Surface)
                        }
                    }
                }
            }

            if (onChangePhoto != null) {
                Surface(
                    modifier = Modifier
                        .padding(end = 4.dp, bottom = 4.dp)
                        .size(40.dp),
                    shape = CircleShape,
                    color = PluckPalette.Primary,
                    shadowElevation = 8.dp,
                    tonalElevation = 0.dp,
                    onClick = onChangePhoto,
                    enabled = !isUploading
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.PhotoCamera,
                            contentDescription = "Change profile photo",
                            tint = PluckPalette.Surface
                        )
                    }
                }
            }
        }

        if (onChangePhoto != null) {
            TextButton(
                onClick = onChangePhoto,
                enabled = !isUploading
            ) {
                Text(if (isUploading) "Uploading…" else "Change Photo")
            }
        }
    }
}
