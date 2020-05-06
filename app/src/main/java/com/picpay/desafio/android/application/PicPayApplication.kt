package com.picpay.desafio.android.application

import android.app.Application
import com.picpay.desafio.android.di.ApplicationModule.dataSourceModule
import com.picpay.desafio.android.di.ApplicationModule.repositoryModule
import com.picpay.desafio.android.di.ApplicationModule.useCaseModule
import com.picpay.desafio.android.di.ApplicationModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PicPayApplication)
            modules(listOf(dataSourceModule, repositoryModule, viewModelModule, useCaseModule))
        }

    }
}