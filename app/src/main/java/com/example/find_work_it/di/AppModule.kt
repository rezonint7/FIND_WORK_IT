package com.example.find_work_it.di

import com.example.find_work_it.common.Constants
import com.example.find_work_it.data.remote.ApiService
import com.example.find_work_it.data.repository.ApiRepositoryImpl
import com.example.find_work_it.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(api : ApiService): ApiRepository{
        return ApiRepositoryImpl(api)
    }
}