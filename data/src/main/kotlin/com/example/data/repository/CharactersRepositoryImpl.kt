package com.example.data.repository

import com.example.data.mapper.CharactersMapper.toDomainModel
import com.example.data.remote.CharactersRemoteDataSource
import com.example.domain.model.Character
import com.example.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
) : CharactersRepository {

    override suspend fun getCharacters(): List<Character> =
        charactersRemoteDataSource.getCharacters().toDomainModel()
}