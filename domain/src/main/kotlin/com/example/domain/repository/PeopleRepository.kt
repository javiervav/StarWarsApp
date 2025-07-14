package com.example.domain.repository

import com.example.domain.model.People

interface PeopleRepository {

    suspend fun getPeople(): List<People>
}