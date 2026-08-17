package com.zykrave.anirumy.core.domain.repository

import com.zykrave.anirumy.core.network.CharacterDetailsQuery
import com.zykrave.anirumy.core.network.api.CharacterApi

class CharacterRepository(
    private val api: CharacterApi,
    defaultPreferencesRepository: DefaultPreferencesRepository,
) : BaseNetworkRepository(defaultPreferencesRepository) {

    fun getCharacterDetails(characterId: Int) = api
        .characterDetailsQuery(characterId)
        .toFlow()
        .asDataResult {
            it.Character
        }

    suspend fun updateCharacterDetailsCache(details: CharacterDetailsQuery.Character) {
        api.updateCharacterDetailsCache(
            data = CharacterDetailsQuery.Data(details)
        )
    }

    fun getCharacterMediaPage(
        characterId: Int,
        page: Int,
        perPage: Int = 25,
    ) = api
        .characterMediaQuery(characterId, page, perPage)
        .toFlow()
        .asPagedResult(page = { it.Character?.media?.pageInfo?.commonPage }) {
            it.Character?.media?.edges?.filterNotNull().orEmpty()
        }
}