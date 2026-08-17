package com.zykrave.anirumy.core.network.api

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.zykrave.anirumy.core.network.NotificationsQuery
import com.zykrave.anirumy.core.network.type.NotificationType

class NotificationsApi (
    private val client: ApolloClient
) {
    fun notificationsQuery(
        typeIn: List<NotificationType>?,
        resetCount: Boolean,
        page: Int,
        perPage: Int,
    ) = client
        .query(
            NotificationsQuery(
                page = Optional.present(page),
                perPage = Optional.present(perPage),
                typeIn = Optional.presentIfNotNull(typeIn),
                resetCount = Optional.present(resetCount)
            )
        )
}