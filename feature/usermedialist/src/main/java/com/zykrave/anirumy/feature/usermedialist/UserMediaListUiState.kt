package com.zykrave.anirumy.feature.usermedialist

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import com.zykrave.anirumy.core.base.state.UiState
import com.zykrave.anirumy.core.model.ItemsPerRow
import com.zykrave.anirumy.core.model.ListStyle
import com.zykrave.anirumy.core.network.fragment.CommonMediaListEntry
import com.zykrave.anirumy.core.network.type.MediaListSort
import com.zykrave.anirumy.core.network.type.MediaListStatus
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.network.type.ScoreFormat

@Stable
data class UserMediaListUiState(
    val mediaType: MediaType,
    val lists: MutableMap<String, List<CommonMediaListEntry>> = mutableMapOf(),
    val orderedListNames: List<String> = emptyList(),
    val selectedListName: String? = null,
    val entries: SnapshotStateList<CommonMediaListEntry> = mutableStateListOf(),
    val status: MediaListStatus? = null,
    val isMyList: Boolean = true,
    val showLowPriority: Boolean = false,
    val lowPriorityColor: Color? = null,
    val mediumPriorityColor: Color? = null,
    val highPriorityColor: Color? = null,
    val userId: Int? = null,
    val selectedItem: CommonMediaListEntry? = null,
    val sort: MediaListSort = MediaListSort.UPDATED_TIME_DESC,
    val listStyle: ListStyle = ListStyle.STANDARD,
    val scoreFormat: ScoreFormat = ScoreFormat.POINT_10,
    val itemsPerRow: ItemsPerRow = ItemsPerRow.DEFAULT,
    val fetchFromNetwork: Boolean = false,
    val sortMenuExpanded: Boolean = false,
    val openNotesDialog: Boolean = false,
    val openSetScoreDialog: Boolean = false,
    val plannedEntriesIds: List<Int> = emptyList(),
    val randomEntryId: Int? = null,
    val isLoadingPlusOne: Boolean = false,
    override val error: String? = null,
    override val isLoading: Boolean = true,
) : UiState() {
    override fun setError(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
}
