package com.crownedjester.soft.gamesinfoandnews.domain.repository

import com.crownedjester.soft.gamesinfoandnews.data.data_source.GamesDataApi
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameBaseData
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameFullData
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameNews
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.Giveaway

class GamesDataManager(private val api: GamesDataApi) : GamesDataRepository {

    override suspend fun retrieveGamesBaseData(
        platform: String,
        category: String,
        sortBy: String
    ): List<GameBaseData> = api.retrieveGamesBaseData(platform, category, sortBy)

    override suspend fun retrieveGamesBaseDataByFilter(
        tag: String,
        platform: String,
        sortBy: String
    ): List<GameBaseData> = api.retrieveGamesBaseDataByFilter(tag, platform, sortBy)

    override suspend fun getGameFullDataById(id: Int): GameFullData =
        api.getGameFullDataById(id)

    override suspend fun retrieveLatestNews(): List<GameNews> =
        api.retrieveLatestNews()

    override suspend fun retrieveGiveaways(): List<Giveaway> =
        api.retrieveGiveaways()

}