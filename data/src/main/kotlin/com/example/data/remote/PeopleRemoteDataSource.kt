package com.example.data.remote

import com.example.data.model.PeopleResponse
import javax.inject.Inject

class PeopleRemoteDataSource @Inject constructor(
) {
    suspend fun getPeople(): List<PeopleResponse> = listOf(
        PeopleResponse(name = "John"),
        PeopleResponse(name = "James"),
    )
}