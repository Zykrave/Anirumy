package com.zykrave.anirumy.core.ui.composables.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.markdown.DefaultMarkdownText

@Composable
fun UpdateDialog(
    releaseName: String,
    releaseBody: String?,
    onUpdateClick: () -> Unit,
    onRemindLaterClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = releaseName) },
        text = {
            Column(modifier = Modifier.heightIn(max = 400.dp)) {
                DefaultMarkdownText(
                    markdown = releaseBody,
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onUpdateClick) {
                Text(text = stringResource(R.string.update))
            }
        },
        dismissButton = {
            TextButton(onClick = onRemindLaterClick) {
                Text(text = stringResource(R.string.remind_me_later))
            }
        },
    )
}
