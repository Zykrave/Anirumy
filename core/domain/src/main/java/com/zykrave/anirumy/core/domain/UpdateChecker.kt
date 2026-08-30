package com.zykrave.anirumy.core.domain

import com.zykrave.anirumy.core.domain.repository.DefaultPreferencesRepository
import com.zykrave.anirumy.core.network.api.GithubReleaseApi
import com.zykrave.anirumy.core.network.api.GithubReleaseResponse
import kotlinx.coroutines.flow.first

sealed interface UpdateCheckResult {
    data class UpdateAvailable(val release: GithubReleaseResponse) : UpdateCheckResult
    data object NoUpdate : UpdateCheckResult
}

class UpdateChecker(
    private val githubReleaseApi: GithubReleaseApi,
    private val preferencesRepository: DefaultPreferencesRepository,
    private val currentVersionName: String,
) {
    suspend fun check(): UpdateCheckResult {
        return try {
            val release = githubReleaseApi.getLatestRelease()
            val latestVersion = release.tag_name.removePrefix("v")

            if (!isNewerVersion(latestVersion, currentVersionName)) {
                return UpdateCheckResult.NoUpdate
            }

            val dismissedTag = preferencesRepository.dismissedUpdateTag.first()
            val dismissedUntil = preferencesRepository.dismissedUpdateUntil.first()

            if (dismissedTag == release.tag_name && System.currentTimeMillis() < dismissedUntil) {
                UpdateCheckResult.NoUpdate
            } else {
                UpdateCheckResult.UpdateAvailable(release)
            }
        } catch (e: Exception) {
            UpdateCheckResult.NoUpdate
        }
    }

    private fun isNewerVersion(latest: String, current: String): Boolean {
        val latestParts = latest.split(".").map { it.toIntOrNull() ?: 0 }
        val currentParts = current.split(".").map { it.toIntOrNull() ?: 0 }
        val maxLength = maxOf(latestParts.size, currentParts.size)
        for (i in 0 until maxLength) {
            val l = latestParts.getOrElse(i) { 0 }
            val c = currentParts.getOrElse(i) { 0 }
            if (l != c) return l > c
        }
        return false
    }
}
