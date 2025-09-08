package com.example.data.mapper

import com.example.data.model.CharacterResponse
import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object CharactersMapper {

    fun Flow<List<CharacterResponse>>.toDomainModel(): Flow<List<Character>> =
        map { characters -> characters.map { it.toDomainModel() } }

    fun CharacterResponse.toDomainModel(): Character {
        return Character(
            name = this.name,
            imageUrl = this.imageUrl,
        )
    }
}