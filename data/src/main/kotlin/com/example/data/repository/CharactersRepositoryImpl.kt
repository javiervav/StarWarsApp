package com.example.data.repository

import com.example.data.mapper.CharactersMapper.toDomainModel
import com.example.data.remote.CharactersRemoteDataSource
import com.example.domain.model.Character
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
) : CharactersRepository {

    override fun getCharacters(): Flow<List<Character>> =
        charactersRemoteDataSource.getCharacters().toDomainModel()
}