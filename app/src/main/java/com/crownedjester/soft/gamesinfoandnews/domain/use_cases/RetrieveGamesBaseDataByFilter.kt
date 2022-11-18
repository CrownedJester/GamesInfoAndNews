package com.crownedjester.soft.gamesinfoandnews.domain.use_cases

import com.crownedjester.soft.gamesinfoandnews.common.Response
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameBaseData
import com.crownedjester.soft.gamesinfoandnews.domain.repository.GamesDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RetrieveGamesBaseDataByFilter(private val repository: GamesDataRepository) {

    operator fun invoke(
        platform: String,
        tag: String,
        sortBy: String
    ): Flow<Response<List<GameBaseData>>> = flow {
        try {
            emit(Response.IsLoading())

            val data = repository.retrieveGamesBaseDataByFilter(tag, platform, sortBy)
            emit(Response.Success(data = data))

            //do some manipulations with data
        } catch (e: HttpException) {
            emit(Response.Error(message = "Check your device network connection"))
        } catch (e: IOException) {
            emit(Response.Error(message = "Something wrong with connection to server"))
        }
    }
}