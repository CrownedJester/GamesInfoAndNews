package com.crownedjester.soft.gamesinfoandnews.representation.games_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crownedjester.soft.gamesinfoandnews.common.Response
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameBaseData
import com.crownedjester.soft.gamesinfoandnews.domain.use_cases.RetrieveGamesBaseData
import com.crownedjester.soft.gamesinfoandnews.representation.common.RemoteDataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class GamesViewModel() : ViewModel(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }

    val retrieveGamesBaseData by inject<RetrieveGamesBaseData>()

    private val _gamesDataStateFlow =
        MutableStateFlow<RemoteDataState<List<GameBaseData>>>(RemoteDataState())
    val gamesDataStateFlow get() = _gamesDataStateFlow.asStateFlow()

    init {
        retrieveGamesData()
    }

    private fun retrieveGamesData() {
        retrieveGamesBaseData(
            platform = "all",
            category = "mmorpg",
            sortBy = "alphabetical"
        ).onEach {
            when (it) {
                is Response.IsLoading -> _gamesDataStateFlow.emit(
                    RemoteDataState(
                        true
                    )
                )

                is Response.Success -> _gamesDataStateFlow.emit(
                    RemoteDataState(
                        data = it.data
                    )
                )

                is Response.Error -> _gamesDataStateFlow.emit(
                    RemoteDataState(error = it.message)
                )

            }

        }.launchIn(viewModelScope)
    }


}