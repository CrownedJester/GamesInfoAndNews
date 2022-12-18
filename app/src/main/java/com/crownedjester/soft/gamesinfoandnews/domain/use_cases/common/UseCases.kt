package com.crownedjester.soft.gamesinfoandnews.domain.use_cases.common

import com.crownedjester.soft.gamesinfoandnews.common.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


fun <T> buildRemoteDataFlow(
    prepareData: suspend () -> T
): Flow<Response<T>> = flow {
    try {

        emit(Response.IsLoading())

        emit(Response.Success(data = prepareData()))

    } catch (e: HttpException) {
        emit(Response.Error(message = "Check your device network connection"))
    } catch (e: IOException) {
        emit(Response.Error(message = "Something wrong with connection to server"))
    }


}