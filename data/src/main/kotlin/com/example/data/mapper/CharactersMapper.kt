package com.example.data.mapper

import com.example.data.model.CharacterResponse
import com.example.domain.model.Character

object CharactersMapper {

    fun List<CharacterResponse>.toDomainModel(): List<Character> =
        this.map { it.toDomainModel() }

    fun CharacterResponse.toDomainModel(): Character {
        return Character(
            name = this.name
        )
    }
}