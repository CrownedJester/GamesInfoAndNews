package com.crownedjester.soft.gamesinfoandnews

import android.app.Application
import com.crownedjester.soft.gamesinfoandnews.di.repositoryModule
import com.crownedjester.soft.gamesinfoandnews.di.restModule
import com.crownedjester.soft.gamesinfoandnews.di.useCasesModule
import com.crownedjester.soft.gamesinfoandnews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, restModule, useCasesModule, viewModelModule))
            androidLogger()
        }
    }
}