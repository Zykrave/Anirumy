package com.zykrave.anirumy.feature.profile.stats.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zykrave.anirumy.core.model.media.localized
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.ui.composables.common.FilterSelectionChip

@Composable
fun MediaTypeChips(
    value: MediaType,
    onValueChanged: (MediaType) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        MediaType.knownEntries.forEach {
            FilterSelectionChip(
                selected = value == it,
                text = it.localized(),
                onClick = { onValueChanged(it) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}