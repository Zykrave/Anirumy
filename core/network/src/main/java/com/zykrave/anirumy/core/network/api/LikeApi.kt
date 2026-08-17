package com.zykrave.anirumy.core.network.api

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.zykrave.anirumy.core.network.ToggleLikeMutation
import com.zykrave.anirumy.core.network.type.LikeableType

class LikeApi(
    private val client: ApolloClient
) {
    fun toggleLikeMutation(
        likeableId: Int,
        type: LikeableType
    ) = client
        .mutation(
            ToggleLikeMutation(
                likeableId = Optional.present(likeableId),
                type = Optional.present(type)
            )
        )
}