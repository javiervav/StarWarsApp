package com.example.data.api

import com.example.data.model.CharacterResponseWrapper
import kotlinx.serialization.InternalSerializationApi
import retrofit2.Response
import retrofit2.http.GET

interface StarWarsRetrofitApi {

    @OptIn(InternalSerializationApi::class)
    @GET("people")
    suspend fun getCharacters(): Response<CharacterResponseWrapper>
}