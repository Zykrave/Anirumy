package com.zykrave.anirumy.core.network.api

import kotlinx.serialization.Serializable
import retrofit2.http.GET

interface GithubReleaseApi {
    @GET("repos/Zykrave/Anirumy/releases/latest")
    suspend fun getLatestRelease(): GithubReleaseResponse
}

@Serializable
data class GithubReleaseResponse(
    val tag_name: String,
    val name: String? = null,
    val body: String? = null,
    val html_url: String,
)