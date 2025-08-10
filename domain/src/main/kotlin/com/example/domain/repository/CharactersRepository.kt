package com.example.domain.repository

import com.example.domain.model.Character

interface CharactersRepository {

    suspend fun getCharacters(): List<Character>
}