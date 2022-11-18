package com.crownedjester.soft.gamesinfoandnews.domain.repository

import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameBaseData
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameFullData
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameNews
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.Giveaway

interface GamesDataRepository {

    suspend fun retrieveGamesBaseData(
        platform: String, category: String, sortBy: String
    ): List<GameBaseData>

    suspend fun retrieveGamesBaseDataByFilter(
        tag: String, platform: String, sortBy: String
    ): List<GameBaseData>

    suspend fun getGameFullDataById(id: Int): GameFullData

    suspend fun retrieveLatestNews(): List<GameNews>

    suspend fun retrieveGiveaways(): List<Giveaway>

}