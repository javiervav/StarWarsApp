package com.example.data.di

import com.example.data.api.AkababRetrofitApi
import com.example.data.api.StarWarsRetrofitApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val STAR_WARS_BASE_URL = "https://swapi.py4e.com/api/" // General info
    private const val AKABAB_BASE_URL = "https://akabab.github.io/starwars-api/api/" // Characters image
    private const val MEDIA_TYPE = "application/json"

    @Provides
    @Singleton
    fun provideNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    // region Star Wars api
    @Provides
    @Singleton
    @Named("swapiRetrofit")
    fun provideStarWarsRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        getRetrofit(STAR_WARS_BASE_URL, okHttpClient, json)

    @Provides
    @Singleton
    fun provideStarWarsRetrofitApi(@Named("swapiRetrofit") retrofit: Retrofit): StarWarsRetrofitApi =
        retrofit.create(StarWarsRetrofitApi::class.java)
    // endregion

    // region Akabab api
    @Provides
    @Singleton
    @Named("akababRetrofit")
    fun provideAkababRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        getRetrofit(AKABAB_BASE_URL, okHttpClient, json)

    @Provides
    @Singleton
    fun provideAkababRetrofitApi(@Named("akababRetrofit") retrofit: Retrofit): AkababRetrofitApi =
        retrofit.create(AkababRetrofitApi::class.java)

    // endregion

    private fun getRetrofit(baseUrl: String, okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(MEDIA_TYPE.toMediaType()))
            .build()
    }
}