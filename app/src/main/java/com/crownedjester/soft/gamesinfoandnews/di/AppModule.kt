package com.crownedjester.soft.gamesinfoandnews.di

import com.crownedjester.soft.gamesinfoandnews.data.data_source.GamesDataApi
import com.crownedjester.soft.gamesinfoandnews.data.data_source.createClient
import com.crownedjester.soft.gamesinfoandnews.domain.repository.GamesDataManager
import com.crownedjester.soft.gamesinfoandnews.domain.repository.GamesDataRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val restModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(GamesDataApi.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<GamesDataApi> {
        get<Retrofit>().create(GamesDataApi::class.java)
    }

}

val repositoryModule = module {

    single<GamesDataRepository> {
        GamesDataManager(get())
    }

}


val useCasesModule = module {

}

