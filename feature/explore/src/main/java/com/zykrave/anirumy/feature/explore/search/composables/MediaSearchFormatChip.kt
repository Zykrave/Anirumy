package com.zykrave.anirumy.feature.explore.search.composables

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.media.MediaFormatLocalizable
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.common.DialogWithCheckboxSelection

@Composable
fun MediaSearchFormatChip(
    mediaType: MediaType,
    selectedMediaFormats: List<MediaFormatLocalizable>,
    onMediaFormatsChanged: (List<MediaFormatLocalizable>) -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        DialogWithCheckboxSelection(
            values = if (mediaType == MediaType.ANIME) MediaFormatLocalizable.animeEntries
            else MediaFormatLocalizable.mangaEntries,
            defaultValues = selectedMediaFormats,
            title = stringResource(R.string.format),
            onConfirm = {
                openDialog = false
                onMediaFormatsChanged(it)
            },
            onDismiss = { openDialog = false }
        )
    }

    FilterChip(
        selected = selectedMediaFormats.isNotEmpty(),
        onClick = { openDialog = true },
        label = { Text(text = stringResource(R.string.format)) },
    )
}