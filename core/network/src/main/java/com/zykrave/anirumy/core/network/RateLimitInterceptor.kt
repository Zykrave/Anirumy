package com.zykrave.anirumy.core.network

import okhttp3.Interceptor
import okhttp3.Response

class RateLimitInterceptor(
    private val maxRetries: Int = 2,
    private val defaultWaitSeconds: Long = 5L,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        var retryCount = 0
        var lastWaitSeconds = defaultWaitSeconds

        while ((response.code == 429) && (retryCount < maxRetries)) {
            val retryAfterHeader = parseRetryAfter(response)
            val waitSeconds = retryAfterHeader ?: if (retryCount == 0) defaultWaitSeconds else lastWaitSeconds * 2
            lastWaitSeconds = waitSeconds

            try {
                Thread.sleep(waitSeconds * 1000L)
            } catch (_: InterruptedException) {
                Thread.currentThread().interrupt()
                return response
            }

            response.close()
            retryCount++
            response = chain.proceed(request)
        }

        return response
    }

    private fun parseRetryAfter(response: Response): Long? {
        return response.header("Retry-After")
            ?.trim()
            ?.toLongOrNull()
            ?.takeIf { it > 0 }
    }
}
