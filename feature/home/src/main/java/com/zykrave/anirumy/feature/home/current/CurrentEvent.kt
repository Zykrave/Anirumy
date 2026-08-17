package com.zykrave.anirumy.feature.home.current

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.model.CurrentListType
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.network.fragment.CommonMediaListEntry

@Immutable
interface CurrentEvent : UiEvent {
    fun refresh()

    fun onClickPlusOne(increment: Int, item: CommonMediaListEntry, type: CurrentListType)

    fun blockPlusOne()

    fun onUpdateListEntry(
        newListEntry: BasicMediaListEntry?,
        type: CurrentListType
    )

    fun selectItem(item: CommonMediaListEntry, type: CurrentListType)

    fun toggleSetScoreDialog(open: Boolean)

    fun setScore(score: Double?)
}