package com.example.data.remote

import com.example.data.api.AkababRetrofitApi
import com.example.data.api.StarWarsRetrofitApi
import com.example.data.model.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class CharactersRemoteDataSource @Inject constructor(
    private val starWarsRetrofitApi: StarWarsRetrofitApi,
    private val akababRetrofitApi: AkababRetrofitApi,
) {

    fun getCharacters(): Flow<List<CharacterResponse>> = flow {
        val response = starWarsRetrofitApi.getCharacters()
        if (response.isSuccessful) {
            val characters = response.body()?.characters.orEmpty()
            emit(characters)

            val updatedCharacters = characters.toMutableList()
            characters.forEachIndexed { index, character ->
                val characterImage = akababRetrofitApi.getCharacterById(index + 1)
                val imageUrl = characterImage.body()?.imageUrl
                if (!imageUrl.isNullOrBlank()) {
                    val updatedCharacter = character.copy(imageUrl = imageUrl)
                    updatedCharacters[index] = updatedCharacter
                    emit(updatedCharacters)
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}