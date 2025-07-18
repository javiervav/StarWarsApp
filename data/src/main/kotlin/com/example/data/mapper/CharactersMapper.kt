package com.example.data.mapper

import com.example.data.model.CharacterResponse
import com.example.domain.model.People

object CharactersMapper {

    fun List<CharacterResponse>.toDomainModel(): List<People> =
        this.map { it.toDomainModel() }

    fun CharacterResponse.toDomainModel(): People {
        return People(
            name = this.name
        )
    }
}