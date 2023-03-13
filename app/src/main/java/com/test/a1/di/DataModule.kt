package com.test.a1.di

import android.app.Application
import android.content.Context
import com.test.a1.data.FootballRepositoryImpl
import com.test.a1.data.network.NetworkApi
import com.test.a1.data.network.NetworkFactory
import com.test.a1.data.network.SharedPreferencesRepositoryImpl
import com.test.a1.domain.FootballRepository
import com.test.a1.domain.SharedPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindFootballRepository(impl: FootballRepositoryImpl) :FootballRepository

    @Binds
    @Singleton
    fun bindSharedPreferencesRepository(impl: SharedPreferencesRepositoryImpl): SharedPreferencesRepository


    @Binds
    fun bindContext(application: Application): Context

    companion object {
        @Provides
        @Singleton
        fun provideNetworkApi(): NetworkApi = NetworkFactory.apiService
    }
}
