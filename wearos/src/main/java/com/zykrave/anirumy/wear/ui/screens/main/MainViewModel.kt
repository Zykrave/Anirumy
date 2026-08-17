package com.zykrave.anirumy.wear.ui.screens.main

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.zykrave.anirumy.core.base.ANIHYOU_SCHEME
import com.zykrave.anirumy.core.base.extensions.firstBlocking
import com.zykrave.anirumy.core.common.viewmodel.UiStateViewModel
import com.zykrave.anirumy.core.domain.repository.DefaultPreferencesRepository
import com.zykrave.anirumy.core.domain.repository.LoginRepository
import com.zykrave.anirumy.core.network.NetworkVariables
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkVariables: NetworkVariables,
    private val defaultPreferencesRepository: DefaultPreferencesRepository,
    private val loginRepository: LoginRepository,
) : UiStateViewModel<MainUiState>(), MainEvent {

    override val initialState = MainUiState()

    override fun onIntentDataReceived(data: Uri?) {
        viewModelScope.launch {
            if (data?.scheme == ANIHYOU_SCHEME) {
                data.getQueryParameter("access_token")?.let { token ->
                    loginRepository.onNewToken(token)
                }
            }
        }
    }

    fun onCreateActivity() {
        val token = defaultPreferencesRepository.accessToken.firstBlocking()
        networkVariables.accessToken = token
        mutableUiState.update { it.copy(isLoggedIn = token != null) }
    }

    init {
        defaultPreferencesRepository.accessToken
            .onEach { value ->
                networkVariables.accessToken = value
                mutableUiState.update { it.copy(isLoggedIn = value != null) }
            }
            .launchIn(viewModelScope)
    }
}