package com.crownedjester.soft.gamesinfoandnews.representation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crownedjester.soft.gamesinfoandnews.representation.common.UiEvent
import com.crownedjester.soft.gamesinfoandnews.representation.common.sendEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {

    }

    fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.sendEvent(Dispatchers.IO + Job(), uiEvent = event)
        }
    }

}