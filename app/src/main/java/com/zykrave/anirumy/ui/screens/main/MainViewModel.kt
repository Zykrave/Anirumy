package com.zykrave.anirumy.ui.screens.main

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zykrave.anirumy.core.base.ANIHYOU_AUTH_RESPONSE
import com.zykrave.anirumy.core.base.ANIHYOU_SCHEME
import com.zykrave.anirumy.core.base.ANIHYOU_WEAR_AUTH
import com.zykrave.anirumy.core.base.ANIHYOU_WEAR_CALLBACK_URL
import com.zykrave.anirumy.core.common.utils.ContextUtils.showToast
import com.zykrave.anirumy.core.domain.repository.DefaultPreferencesRepository
import com.zykrave.anirumy.core.domain.repository.LoginRepository
import com.zykrave.anirumy.core.model.DefaultTab
import com.zykrave.anirumy.core.network.NetworkVariables
import com.zykrave.anirumy.core.network.type.ScoreFormat
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.startRemoteActivity
import com.materialkolor.PaletteStyle
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkVariables: NetworkVariables,
    private val loginRepository: LoginRepository,
    private val defaultPreferencesRepository: DefaultPreferencesRepository,
) : ViewModel(), MainEvent {

    val accessToken = defaultPreferencesRepository.accessToken

    val isLoggedIn = defaultPreferencesRepository.isLoggedIn

    val homeTab = defaultPreferencesRepository.defaultHomeTab

    val theme = defaultPreferencesRepository.theme

    val useBlackColors = defaultPreferencesRepository.useBlackColors

    val appColor = defaultPreferencesRepository.appColor

    val appColorMode = defaultPreferencesRepository.appColorMode

    val paletteStyle = defaultPreferencesRepository.colorPalette.map { value ->
        value?.let { PaletteStyle.valueOf(it) } ?: PaletteStyle.Expressive
    }

    val blurAdultContent = defaultPreferencesRepository.blurAdult

    val scoreFormat = defaultPreferencesRepository.scoreFormat.map {
        it ?: ScoreFormat.POINT_10_DECIMAL
    }

    val hideScores = defaultPreferencesRepository.hideScores

    override fun saveLastTab(index: Int) {
        viewModelScope.launch {
            defaultPreferencesRepository.setLastTab(index)
        }
    }

    suspend fun getStartTab(): Int {
        val defaultTab = defaultPreferencesRepository.defaultTab.first()
        return if (defaultTab == null || defaultTab == DefaultTab.LAST_USED) {
            defaultPreferencesRepository.lastTab.first()
        } else {
            defaultTab.ordinal - 1
        }
    }

    fun setToken(token: String?) {
        networkVariables.accessToken = token
    }

    fun onIntentDataReceived(context: Context, data: Uri?) = viewModelScope.launch {
        if (data?.scheme == ANIHYOU_SCHEME) {
            when {
                data.toString().contains(ANIHYOU_AUTH_RESPONSE) -> loginRepository.parseRedirectUri(data)
                data.toString().contains(ANIHYOU_WEAR_AUTH) -> sendAuthTokenToWearable(context)
            }
        }
    }

    private fun sendAuthTokenToWearable(context: Context) {
        viewModelScope.launch {
            val token = accessToken.first()
            if (token == null) {
                context.showToast(R.string.not_logged_text)
            } else {
                val data = "${ANIHYOU_WEAR_CALLBACK_URL}?access_token=$token".toUri()
                context.startRemoteActivity(data)
            }
        }
    }

    init {
        accessToken
            .onEach { setToken(it) }
            .launchIn(viewModelScope)
    }
}