package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.features.home.HomeViewModel
import com.picpay.desafio.android.model.data.datasources.cache.AppDatabase
import com.picpay.desafio.android.model.data.datasources.cache.CacheDataSource
import com.picpay.desafio.android.model.data.datasources.cache.CacheDataSourceImpl
import com.picpay.desafio.android.model.data.datasources.remote.MockDataSource
import com.picpay.desafio.android.model.data.datasources.remote.RemoteDataSource
import com.picpay.desafio.android.model.data.datasources.remote.RemoteDataSourceImpl
import com.picpay.desafio.android.model.data.repositories.RemoteRepository
import com.picpay.desafio.android.model.data.repositories.RemoteRepositoryImpl
import com.picpay.desafio.android.model.data.services.PicPayService
import com.picpay.desafio.android.model.domain.usecases.HomeUseCase
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApplicationModule {
    val dataSourceModule = module {
        single { GsonBuilder().create() }
        single { OkHttpClient.Builder().build() }
        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_API)
                .client( get() )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(PicPayService::class.java)
        }

        single { AppDatabase.createDataBase( context = androidContext() ) }

        factory<RemoteDataSource> {
            when(BuildConfig.BUILD_TYPE){
                "mock" -> MockDataSource( gson = get(), context = get() )
                else -> RemoteDataSourceImpl( service = get())
            }
        }

        factory<CacheDataSource> { CacheDataSourceImpl( userDAO = get() ) }


    }

    val repositoryModule = module {
        factory<RemoteRepository> {
            RemoteRepositoryImpl( remoteDataSource = get(), cacheDataSource = get() )
        }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel( useCase = get() ) }
    }

    val useCaseModule = module {
        factory { HomeUseCase( repository = get() ) }
    }

}