package com.crownedjester.soft.gamesinfoandnews.domain.use_cases

import com.crownedjester.soft.gamesinfoandnews.common.Response
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameFullData
import com.crownedjester.soft.gamesinfoandnews.domain.repository.GamesDataRepository
import com.crownedjester.soft.gamesinfoandnews.domain.use_cases.common.buildRemoteDataFlow
import kotlinx.coroutines.flow.Flow

class GetGameFullDataById(private val repository: GamesDataRepository) {

    operator fun invoke(id: Int): Flow<Response<GameFullData>> =
        buildRemoteDataFlow(
            prepareData = {
                //do manipulation with data
                repository.getGameFullDataById(id)
            }
        )
    /*flow {
    try {

        emit(Response.IsLoading())

        emit(Response.Success(repository.getGameFullDataById(id)))

    } catch (e: HttpException) {
        emit(Response.Error(message = "Check your device network connection"))
    } catch (e: IOException) {
        emit(Response.Error(message = "Something wrong with connection to server"))
    }
}
*/
}