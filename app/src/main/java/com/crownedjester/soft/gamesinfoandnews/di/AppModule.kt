package com.crownedjester.soft.gamesinfoandnews.di

import com.crownedjester.soft.gamesinfoandnews.data.data_source.GamesDataApi
import com.crownedjester.soft.gamesinfoandnews.data.data_source.createClient
import com.crownedjester.soft.gamesinfoandnews.domain.repository.GamesDataManager
import com.crownedjester.soft.gamesinfoandnews.domain.repository.GamesDataRepository
import com.crownedjester.soft.gamesinfoandnews.domain.use_cases.GetGameFullDataById
import com.crownedjester.soft.gamesinfoandnews.domain.use_cases.RetrieveGamesBaseData
import com.crownedjester.soft.gamesinfoandnews.representation.SharedViewModel
import com.crownedjester.soft.gamesinfoandnews.representation.games_detail_screen.viewmodel.GameDetailViewModel
import com.crownedjester.soft.gamesinfoandnews.representation.games_screen.viewmodel.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {

    viewModelOf(::GameDetailViewModel)

    viewModelOf(::GamesViewModel)

    viewModelOf(::SharedViewModel)

}

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

    scope<GamesViewModel> {
        scoped {
            RetrieveGamesBaseData(get())
        }
    }

    scope<GameDetailViewModel> {
        scoped {
            GetGameFullDataById(get())
        }
    }


}

