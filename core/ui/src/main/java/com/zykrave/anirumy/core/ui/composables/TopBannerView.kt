package com.zykrave.anirumy.core.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.zykrave.anirumy.core.ui.theme.AniHyouTheme

@Composable
fun TopBannerView(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    fallbackColor: Color? = null,
    height: Dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "banner",
                placeholder = ColorPainter(MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .background(
                        color = fallbackColor ?: MaterialTheme.colorScheme.outline
                    )
                    .fillMaxSize()
            )
        }
        
        // Layer 1 (contrast/readability): vertical gradient scrim
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0.45f),
                            Color.Black.copy(alpha = 0.05f),
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
        )

        // Layer 2 (glass sheen): top ~40% glass sheen hint
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.White.copy(alpha = 0.05f),
                            Color.White.copy(alpha = 0f)
                        )
                    )
                )
        )
    }
}

@Preview
@Composable
private fun TopBannerViewPreview() {
    AniHyouTheme {
        Surface {
            TopBannerView(
                imageUrl = null,
                fallbackColor = MaterialTheme.colorScheme.secondary,
                height = 250.dp
            )
        }
    }
}