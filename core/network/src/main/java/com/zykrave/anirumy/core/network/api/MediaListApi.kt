package com.zykrave.anirumy.core.network.api

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.apollographql.cache.normalized.FetchPolicy
import com.apollographql.cache.normalized.api.CacheKey
import com.apollographql.cache.normalized.apolloStore
import com.apollographql.cache.normalized.fetchPolicy
import com.zykrave.anirumy.core.network.DeleteMediaListMutation
import com.zykrave.anirumy.core.network.MediaListCustomListsQuery
import com.zykrave.anirumy.core.network.MediaListIdsQuery
import com.zykrave.anirumy.core.network.MySeasonalAnimeQuery
import com.zykrave.anirumy.core.network.UpdateEntryCustomListsMutation
import com.zykrave.anirumy.core.network.UpdateEntryMutation
import com.zykrave.anirumy.core.network.UserListCollectionQuery
import com.zykrave.anirumy.core.network.UserMediaListQuery
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntryImpl
import com.zykrave.anirumy.core.network.type.FuzzyDateInput
import com.zykrave.anirumy.core.network.type.MediaListSort
import com.zykrave.anirumy.core.network.type.MediaListStatus
import com.zykrave.anirumy.core.network.type.MediaSeason
import com.zykrave.anirumy.core.network.type.MediaSort
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.network.type.ScoreFormat

class MediaListApi(
    private val client: ApolloClient
) {
    fun mediaListCollection(
        userId: Int,
        mediaType: MediaType,
        sort: List<MediaListSort>,
        fetchFromNetwork: Boolean,
        chunk: Int?,
        perChunk: Int?
    ) = client
        .query(
            UserListCollectionQuery(
                userId = Optional.present(userId),
                type = Optional.present(mediaType),
                sort = Optional.present(sort),
                chunk = Optional.presentIfNotNull(chunk),
                perChunk = Optional.presentIfNotNull(perChunk)
            )
        )
        .fetchPolicy(if (fetchFromNetwork) FetchPolicy.NetworkFirst else FetchPolicy.CacheFirst)

    fun userMediaList(
        userId: Int,
        mediaType: MediaType,
        statusIn: List<MediaListStatus>?,
        sort: List<MediaListSort>,
        scoreFormat: ScoreFormat,
        fetchFromNetwork: Boolean,
        page: Int?,
        perPage: Int?,
    ) = client
        .query(
            UserMediaListQuery(
                userId = Optional.present(userId),
                type = Optional.present(mediaType),
                statusIn = Optional.presentIfNotNull(statusIn),
                sort = Optional.present(sort),
                scoreFormat = Optional.present(scoreFormat),
                page = Optional.presentIfNotNull(page),
                perPage = Optional.presentIfNotNull(perPage),
            )
        )
        .fetchPolicy(if (fetchFromNetwork) FetchPolicy.NetworkFirst else FetchPolicy.CacheFirst)

    fun mySeasonalAnimeQuery(
        season: MediaSeason,
        seasonYear: Int,
        sort: List<MediaSort>,
        fetchFromNetwork: Boolean,
        page: Int,
        perPage: Int,
    ) = client
        .query(
            MySeasonalAnimeQuery(
                page = Optional.present(page),
                perPage = Optional.present(perPage),
                season = Optional.present(season),
                seasonYear = Optional.present(seasonYear),
                sort = Optional.present(sort)
            )
        )
        .fetchPolicy(if (fetchFromNetwork) FetchPolicy.NetworkFirst else FetchPolicy.CacheFirst)

    suspend fun updateMediaListCache(data: BasicMediaListEntry) {
        client.apolloStore
            .writeFragment(
                fragment = BasicMediaListEntryImpl(),
                cacheKey = CacheKey("${data.__typename}:${data.id} ${data.mediaId}"),
                data = data,
            )
    }

    fun updateEntryMutation(
        mediaId: Int,
        status: MediaListStatus?,
        score: Double?,
        advancedScores: List<Double>?,
        progress: Int?,
        progressVolumes: Int?,
        startedAt: FuzzyDateInput?,
        completedAt: FuzzyDateInput?,
        repeat: Int?,
        private: Boolean?,
        hiddenFromStatusLists: Boolean?,
        notes: String?,
        priority: Int?,
    ) = client
        .mutation(
            UpdateEntryMutation(
                mediaId = Optional.present(mediaId),
                status = Optional.presentIfNotNull(status),
                score = Optional.presentIfNotNull(score),
                advancedScores = Optional.presentIfNotNull(advancedScores),
                progress = Optional.presentIfNotNull(progress),
                progressVolumes = Optional.presentIfNotNull(progressVolumes),
                startedAt = Optional.present(startedAt),
                completedAt = Optional.present(completedAt),
                repeat = Optional.presentIfNotNull(repeat),
                private = Optional.presentIfNotNull(private),
                hiddenFromStatusLists = Optional.presentIfNotNull(hiddenFromStatusLists),
                notes = Optional.presentIfNotNull(notes),
                priority = Optional.presentIfNotNull(priority)
            )
        )

    fun updateEntryCustomListsMutation(
        mediaId: Int,
        customLists: List<String?>,
    ) = client
        .mutation(
            UpdateEntryCustomListsMutation(
                mediaId = Optional.present(mediaId),
                customLists = Optional.present(customLists)
            )
        )

    fun deleteMediaListMutation(id: Int) = client
        .mutation(
            DeleteMediaListMutation(
                mediaListEntryId = Optional.present(id)
            )
        )

    fun mediaListCustomLists(
        id: Int,
        userId: Int
    ) = client
        .query(
            MediaListCustomListsQuery(
                id = Optional.present(id),
                userId = Optional.present(userId)
            )
        )

    fun mediaListIds(
        userId: Int,
        type: MediaType,
        status: MediaListStatus?,
        chunk: Int,
        perChunk: Int
    ) = client
        .query(
            MediaListIdsQuery(
                type = Optional.present(type),
                userId = Optional.present(userId),
                status = Optional.presentIfNotNull(status),
                chunk = Optional.present(chunk),
                perChunk = Optional.present(perChunk)
            )
        )
}