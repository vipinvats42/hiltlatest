package com.test.vipin.model.di

import com.test.vipin.model.api.ApiHelper
import com.test.vipin.model.api.ApiHelperImpl
import com.test.vipin.model.api.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {
    companion object {
        @Provides
        fun provideBaseUrl() = "https://5e510330f2c0d300147c034c.mockapi.io"



        @Provides
        @Singleton
        fun provideOkHttpClient() = run {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }


        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()


        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl

    @Binds
    @Singleton
    abstract fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper

}