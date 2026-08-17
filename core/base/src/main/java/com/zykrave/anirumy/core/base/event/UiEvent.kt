package com.zykrave.anirumy.core.base.event

interface UiEvent {
    fun showError(error: String)
    fun onErrorDisplayed()
}