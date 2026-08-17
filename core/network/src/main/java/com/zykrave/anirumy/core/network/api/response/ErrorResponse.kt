package com.zykrave.anirumy.core.network.api.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val errors: List<Error>
) {
    @Serializable
    data class Error(
        val message: String,
    )
}