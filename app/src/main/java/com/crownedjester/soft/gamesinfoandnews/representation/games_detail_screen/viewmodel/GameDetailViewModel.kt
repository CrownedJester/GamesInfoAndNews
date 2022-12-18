package com.crownedjester.soft.gamesinfoandnews.representation.games_detail_screen.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crownedjester.soft.gamesinfoandnews.common.Response
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameFullData
import com.crownedjester.soft.gamesinfoandnews.domain.use_cases.GetGameFullDataById
import com.crownedjester.soft.gamesinfoandnews.representation.bundle.BundlePrefs
import com.crownedjester.soft.gamesinfoandnews.representation.common.RemoteDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class GameDetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel(),
    KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }

    private val getGameDataById by inject<GetGameFullDataById>()

    private val _currentGameStateFlow = MutableStateFlow(RemoteDataState<GameFullData>())
    val currentGameDataStateFlow get() = _currentGameStateFlow.asStateFlow()

    init {
        getGameData()
    }

    private fun getGameData() {
        viewModelScope.launch(Dispatchers.IO + Job()) {

            savedStateHandle.getStateFlow<Any>(BundlePrefs.GAME_ID_KEY, -1).collectLatest { id ->

                getGameDataById(id as Int).onEach { response ->
                    when (response) {
                        is Response.Error -> _currentGameStateFlow.emit(RemoteDataState(error = response.message))

                        is Response.IsLoading -> _currentGameStateFlow.emit(
                            RemoteDataState(
                                isLoading = true
                            )
                        )

                        is Response.Success -> {
                            _currentGameStateFlow.emit(RemoteDataState(data = response.data))
                            Log.i("GameDetailViewModel", response.data.toString())
                        }
                    }
                }.launchIn(viewModelScope)
            }

        }

    }

}