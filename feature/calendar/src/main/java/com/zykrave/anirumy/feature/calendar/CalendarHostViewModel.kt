package com.zykrave.anirumy.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zykrave.anirumy.core.domain.repository.DefaultPreferencesRepository
import kotlinx.coroutines.launch

class CalendarHostViewModel(
    private val defaultPreferencesRepository: DefaultPreferencesRepository
): ViewModel() {

    val onMyList = defaultPreferencesRepository.calendarOnMyList

    fun onMyListChanged(value: Boolean?) = viewModelScope.launch {
        defaultPreferencesRepository.setCalendarOnMyList(value)
    }
}