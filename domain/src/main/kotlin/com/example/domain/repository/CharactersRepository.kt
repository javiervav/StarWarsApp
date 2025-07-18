package com.example.domain.repository

import com.example.domain.model.People

interface CharactersRepository {

    suspend fun getPeople(): List<People>
}