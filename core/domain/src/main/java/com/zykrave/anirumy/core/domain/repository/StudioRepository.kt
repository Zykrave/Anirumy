package com.zykrave.anirumy.core.domain.repository

import com.zykrave.anirumy.core.network.StudioDetailsQuery
import com.zykrave.anirumy.core.network.api.StudioApi

class StudioRepository(
    private val api: StudioApi,
    defaultPreferencesRepository: DefaultPreferencesRepository,
) : BaseNetworkRepository(defaultPreferencesRepository) {

    fun getStudioDetails(
        studioId: Int,
        perPage: Int = 25,
    ) = api
        .studioDetailsQuery(studioId, perPage)
        .toFlow()
        .asDataResult {
            it.Studio
        }

    suspend fun updateStudioDetailsCache(details: StudioDetailsQuery.Studio) {
        api.updateStudioDetailsCache(
            data = StudioDetailsQuery.Data(details)
        )
    }

    fun getStudioMediaPage(
        studioId: Int,
        page: Int,
        perPage: Int = 25,
    ) = api
        .studioMediaQuery(studioId, page, perPage)
        .toFlow()
        .asPagedResult(page = { it.Studio?.media?.pageInfo?.commonPage }) {
            it.Studio?.media?.commonStudioMedia?.nodes?.filterNotNull().orEmpty()
        }
}