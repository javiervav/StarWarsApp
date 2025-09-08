package com.example.domain.usecase

import com.example.domain.model.Character
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {

    fun invoke(): Flow<List<Character>> {
        return charactersRepository.getCharacters()
    }
}