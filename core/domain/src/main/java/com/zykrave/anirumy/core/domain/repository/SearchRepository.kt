package com.zykrave.anirumy.core.domain.repository

import com.zykrave.anirumy.core.model.genre.GenresAndTags
import com.zykrave.anirumy.core.model.genre.SelectableGenre
import com.zykrave.anirumy.core.model.media.CountryOfOrigin
import com.zykrave.anirumy.core.network.api.CharacterApi
import com.zykrave.anirumy.core.network.api.MediaApi
import com.zykrave.anirumy.core.network.api.StaffApi
import com.zykrave.anirumy.core.network.api.StudioApi
import com.zykrave.anirumy.core.network.api.UserApi
import com.zykrave.anirumy.core.network.type.MediaFormat
import com.zykrave.anirumy.core.network.type.MediaSeason
import com.zykrave.anirumy.core.network.type.MediaSort
import com.zykrave.anirumy.core.network.type.MediaSource
import com.zykrave.anirumy.core.network.type.MediaStatus
import com.zykrave.anirumy.core.network.type.MediaType

class SearchRepository(
    private val mediaApi: MediaApi,
    private val characterApi: CharacterApi,
    private val staffApi: StaffApi,
    private val studioApi: StudioApi,
    private val userApi: UserApi,
    defaultPreferencesRepository: DefaultPreferencesRepository,
) : BaseNetworkRepository(defaultPreferencesRepository) {

    fun searchMedia(
        mediaType: MediaType,
        query: String,
        sort: List<MediaSort> = listOf(MediaSort.SEARCH_MATCH),
        genreIn: List<String>? = null,
        genreNotIn: List<String>? = null,
        tagIn: List<String>? = null,
        tagNotIn: List<String>? = null,
        minimumTagPercentage: Int? = null,
        formatIn: List<MediaFormat>? = null,
        statusIn: List<MediaStatus>? = null,
        episodesLesser: Int? = null,
        episodesGreater: Int? = null,
        durationLesser: Int? = null,
        durationGreater: Int? = null,
        chaptersLesser: Int? = null,
        chaptersGreater: Int? = null,
        volumesLesser: Int? = null,
        volumesGreater: Int? = null,
        averageScoreLesser: Int? = null,
        averageScoreGreater: Int? = null,
        startYear: Int? = null,
        endYear: Int? = null,
        season: MediaSeason? = null,
        onList: Boolean? = null,
        isLicensed: Boolean? = null,
        isAdult: Boolean? = null,
        country: CountryOfOrigin? = null,
        sourceIn: List<MediaSource>? = null,
        page: Int,
        perPage: Int = 25,
    ) = mediaApi
        .searchMediaQuery(
            mediaType,
            query,
            sort,
            genreIn,
            genreNotIn,
            tagIn,
            tagNotIn,
            minimumTagPercentage,
            formatIn,
            statusIn,
            episodesLesser,
            episodesGreater,
            durationLesser,
            durationGreater,
            chaptersLesser,
            chaptersGreater,
            volumesLesser,
            volumesGreater,
            averageScoreLesser,
            averageScoreGreater,
            startYear,
            endYear,
            season,
            onList,
            isLicensed,
            isAdult,
            country?.toDto(),
            sourceIn,
            page,
            perPage
        )
        .toFlow()
        .asPagedResult(page = { it.Page?.pageInfo?.commonPage }) {
            it.Page?.media?.filterNotNull().orEmpty()
        }

    fun searchCharacter(
        query: String,
        page: Int = 1,
        perPage: Int = 25,
    ) = characterApi
        .searchCharacterQuery(query, page, perPage)
        .toFlow()
        .asPagedResult(page = { it.Page?.pageInfo?.commonPage }) {
            it.Page?.characters?.filterNotNull().orEmpty()
        }

    fun searchStaff(
        query: String,
        page: Int = 1,
        perPage: Int = 25,
    ) = staffApi
        .searchStaffQuery(query, page, perPage)
        .toFlow()
        .asPagedResult(page = { it.Page?.pageInfo?.commonPage }) {
            it.Page?.staff?.filterNotNull().orEmpty()
        }

    fun searchStudio(
        query: String,
        page: Int = 1,
        perPage: Int = 25,
    ) = studioApi
        .searchStudioQuery(query, page, perPage)
        .toFlow()
        .asPagedResult(page = { it.Page?.pageInfo?.commonPage }) {
            it.Page?.studios?.filterNotNull().orEmpty()
        }

    fun searchUser(
        query: String,
        page: Int = 1,
        perPage: Int = 25,
    ) = userApi
        .searchUserQuery(query, page, perPage)
        .toFlow()
        .asPagedResult(page = { it.Page?.pageInfo?.commonPage }) {
            it.Page?.users?.filterNotNull().orEmpty()
        }

    fun getGenreTagCollection() = mediaApi
        .genreTagCollectionQuery()
        .toFlow()
        .asDataResult { data ->
            GenresAndTags(
                genres = data.GenreCollection?.filterNotNull().orEmpty()
                    .map { SelectableGenre(it) },
                tags = data.MediaTagCollection?.filterNotNull()?.map { it.name }.orEmpty()
                    .map { SelectableGenre(it) }
            )
        }
}