package com.zykrave.anirumy.feature.explore.search.composables

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.media.MediaStatusLocalizable
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.common.DialogWithCheckboxSelection

@Composable
fun MediaSearchStatusChip(
    selectedMediaStatuses: List<MediaStatusLocalizable>,
    onMediaStatusesChanged: (List<MediaStatusLocalizable>) -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        DialogWithCheckboxSelection(
            values = MediaStatusLocalizable.entries,
            defaultValues = selectedMediaStatuses,
            title = stringResource(R.string.media_status),
            onConfirm = {
                openDialog = false
                onMediaStatusesChanged(it)
            },
            onDismiss = { openDialog = false }
        )
    }

    FilterChip(
        selected = selectedMediaStatuses.isNotEmpty(),
        onClick = { openDialog = true },
        label = { Text(text = stringResource(R.string.media_status)) },
    )
}