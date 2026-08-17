package com.zykrave.anirumy.feature.usermedialist.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.zykrave.anirumy.core.model.media.asMediaListStatus
import com.zykrave.anirumy.core.model.media.icon
import com.zykrave.anirumy.core.model.media.localizedListStatus
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.sheet.SelectionSheet
import com.zykrave.anirumy.core.ui.composables.sheet.SelectionSheetItem
import com.zykrave.anirumy.feature.usermedialist.UserMediaListUiState
import kotlinx.coroutines.CoroutineScope

@Composable
fun ListSelectSheet(
    uiState: UserMediaListUiState,
    scope: CoroutineScope,
    bottomPadding: Dp,
    onListChanged: (String?) -> Unit,
    onDismiss: () -> Unit,
) {
    SelectionSheet(
        scope = scope,
        bottomPadding = bottomPadding,
        onDismiss = onDismiss,
    ) {
        val totalCount = remember(uiState.lists) { uiState.lists.values.sumOf { it.size } }
        SelectionSheetItem(
            name = stringResource(R.string.all),
            icon = R.drawable.list_alt_24,
            count = totalCount,
            isSelected = uiState.selectedListName == null,
            onClick = {
                onListChanged(null)
                onDismiss()
            }
        )
        uiState.orderedListNames.forEach { name ->
            val count = remember(name) { uiState.lists[name]?.size ?: 0 }
            if (count > 0) {
                SelectionSheetItem(
                    name = name.localizedListStatus(),
                    icon = name.asMediaListStatus()?.icon(),
                    count = count,
                    isSelected = uiState.selectedListName == name,
                    onClick = {
                        onListChanged(name)
                        onDismiss()
                    }
                )
            }
        }
    }
}