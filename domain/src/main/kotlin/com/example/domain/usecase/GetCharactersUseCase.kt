package com.example.domain.usecase

import com.example.domain.model.People
import com.example.domain.repository.PeopleRepository

class GetCharactersUseCase(
    private val peopleRepository: PeopleRepository
) {

    suspend fun invoke(): List<People> {
        return peopleRepository.getPeople()
    }
}