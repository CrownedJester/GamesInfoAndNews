package com.crownedjester.soft.gamesinfoandnews.domain.use_cases

import com.crownedjester.soft.gamesinfoandnews.domain.repository.GamesDataRepository

class GetGameFullDataById(private val repository: GamesDataRepository) {

    suspend operator fun invoke(id: Int) =
        repository.getGameFullDataById(id)
}