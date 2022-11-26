package com.crownedjester.soft.gamesinfoandnews.representation.common

data class RemoteDataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = ""
)

