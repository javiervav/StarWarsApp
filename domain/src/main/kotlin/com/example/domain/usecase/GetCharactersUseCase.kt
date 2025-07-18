package com.example.domain.usecase

import com.example.domain.model.People
import com.example.domain.repository.CharactersRepository

class GetCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {

    suspend fun invoke(): List<People> {
        return charactersRepository.getPeople()
    }
}