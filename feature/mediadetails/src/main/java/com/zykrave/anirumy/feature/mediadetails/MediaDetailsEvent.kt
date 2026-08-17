package com.zykrave.anirumy.feature.mediadetails

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.network.fragment.MediaCharacter
import com.zykrave.anirumy.core.network.type.RecommendationRating

@Immutable
interface MediaDetailsEvent : UiEvent {
    fun onUpdateListEntry(newListEntry: BasicMediaListEntry?)
    fun toggleFavorite()
    fun fetchCharactersAndStaff()
    fun fetchRelationsAndRecommendations()
    fun fetchStats()
    fun fetchThreads()
    fun fetchReviews()
    fun fetchActivity()
    fun showVoiceActorsSheet(character: MediaCharacter)
    fun hideVoiceActorSheet()
    fun onVoteClick(recommendedMediaId: Int, recommendationId: Int, rating: RecommendationRating)
}