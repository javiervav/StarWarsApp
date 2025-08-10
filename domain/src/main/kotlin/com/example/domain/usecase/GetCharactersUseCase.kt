package com.example.domain.usecase

import com.example.domain.model.Character
import com.example.domain.repository.CharactersRepository

class GetCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {

    suspend fun invoke(): List<Character> {
        return charactersRepository.getCharacters()
    }
}