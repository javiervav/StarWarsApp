package com.example.data.repository

import com.example.data.mapper.PeopleMapper.toDomainModel
import com.example.data.remote.PeopleRemoteDataSource
import com.example.domain.model.People
import com.example.domain.repository.PeopleRepository
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val peopleRemoteDataSource: PeopleRemoteDataSource,
) : PeopleRepository {

    override suspend fun getPeople(): List<People> =
        peopleRemoteDataSource.getPeople().toDomainModel()
}