package com.example.find_work_it.di

import android.content.Context
import com.example.find_work_it.common.Constants
import com.example.find_work_it.auth.AuthorizationServiceApp
import com.example.find_work_it.data.remote.ApiService
import com.example.find_work_it.data.repository.ApiRepositoryImpl
import com.example.find_work_it.data.shared_prefs.SharedPrefsHelper
import com.example.find_work_it.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.openid.appauth.AuthorizationService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPrefsHelper(@ApplicationContext context: Context): SharedPrefsHelper {
        return SharedPrefsHelper.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideApi(sharedPrefsHelper: SharedPrefsHelper): ApiService{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${sharedPrefsHelper.getTokens()?.access_token}")
                    .addHeader("User-Agent", "FINDWORKIT/0.7.1 (rezonint@gmail.com)")
                    .build()
                chain.proceed(request)
            }
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideApiRepository(api : ApiService): ApiRepository{
        return ApiRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthorizationService(@ApplicationContext context : Context) : AuthorizationService { return AuthorizationService(context) }

    @Provides
    @Singleton
    fun provideAuthorizationServiceApp() : AuthorizationServiceApp { return AuthorizationServiceApp() }
}