package com.crownedjester.soft.gamesinfoandnews.representation.common

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

sealed class UiEvent {
    data class OnNavigate<out T : Any>(val resId: Int, val arg: T? = null) : UiEvent()
    data class ShowToast(val message: String) : UiEvent()
    object OnBack : UiEvent()

}

suspend fun Channel<UiEvent>.sendEvent(coroutineContext: CoroutineContext, uiEvent: UiEvent) =
    withContext(coroutineContext) {
        this@sendEvent.send(uiEvent)
    }
