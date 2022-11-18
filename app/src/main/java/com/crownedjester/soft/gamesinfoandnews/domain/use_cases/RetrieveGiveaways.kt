package com.crownedjester.soft.gamesinfoandnews.domain.use_cases

import com.crownedjester.soft.gamesinfoandnews.common.Response
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.Giveaway
import com.crownedjester.soft.gamesinfoandnews.domain.repository.GamesDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RetrieveGiveaways(private val repository: GamesDataRepository) {

    operator fun invoke(): Flow<Response<List<Giveaway>>> = flow {
        try {
            emit(Response.IsLoading())

            val data = repository.retrieveGiveaways()
            emit(Response.Success(data = data))

            //do some manipulations with data
        } catch (e: HttpException) {
            emit(Response.Error(message = "Check your device network connection"))
        } catch (e: IOException) {
            emit(Response.Error(message = "Something wrong with connection to server"))
        }
    }
}