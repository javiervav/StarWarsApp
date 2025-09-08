package com.example.data.remote

import com.example.data.api.StarWarsRetrofitApi
import com.example.data.model.CharacterResponse
import javax.inject.Inject

internal class CharactersRemoteDataSource @Inject constructor(
    private val starWarsRetrofitApi: StarWarsRetrofitApi,
) {

    suspend fun getCharacters(): List<CharacterResponse> {
        val response = starWarsRetrofitApi.getCharacters()
        return if (response.isSuccessful) {
            response.body()?.characters.orEmpty()
        } else {
            emptyList()
        }
    }
}