package com.example.find_work_it.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import android.preference.PreferenceManager
import com.example.find_work_it.common.Constants
import com.example.find_work_it.common.SharedPrefsConstants
import com.example.find_work_it.common.autorization.AuthorizationServiceApp
import com.example.find_work_it.data.remote.ApiService
import com.example.find_work_it.data.repository.ApiRepositoryImpl
import com.example.find_work_it.data.repository.SharedPrefsRepositoryImpl
import com.example.find_work_it.domain.repository.ApiRepository
import com.example.find_work_it.domain.repository.SharedPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.openid.appauth.AuthorizationService
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

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SharedPrefsConstants.APP_NAME_SHAREDPREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesRepository(sharedPrefs : SharedPreferences) : SharedPrefsRepository{
        return SharedPrefsRepositoryImpl(sharedPrefs)
    }

    @Provides
    @Singleton
    fun provideAuthorizationService(@ApplicationContext context : Context) : AuthorizationService { return AuthorizationService(context) }

    @Provides
    @Singleton
    fun provideAuthorizationServiceApp() : AuthorizationServiceApp { return AuthorizationServiceApp() }
}