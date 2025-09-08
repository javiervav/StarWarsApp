package com.example.data.api

import com.example.data.model.CharacterImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface AkababRetrofitApi {

    @GET("id/{id}.json")
    suspend fun getCharacterById(@Path("id") id: String): Response<CharacterImageResponse>
}