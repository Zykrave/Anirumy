package com.zykrave.anirumy.core.ui.composables.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zykrave.anirumy.core.ui.theme.AniHyouTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SmallCircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    LoadingIndicator(
        modifier = modifier.size(20.dp),
        color = color,
    )
}

@Preview
@Composable
private fun ProgressIndicatorPreview() {
    AniHyouTheme {
        Surface {
            SmallCircularProgressIndicator()
        }
    }
}