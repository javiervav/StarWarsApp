package com.example.domain.usecase

import com.example.domain.getCharacter
import com.example.domain.repository.CharactersRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCharactersUseCaseTest {

    private val charactersRepository: CharactersRepository = mockk()
    private val getCharactersUseCase = GetCharactersUseCase(
        charactersRepository = charactersRepository
    )

    @Test
    fun `invoke should return list of characters`() = runTest {
        val characters = listOf(
            getCharacter("John Doe"),
            getCharacter("Jane Doe"),
        )
        coEvery { charactersRepository.getCharacters() } returns flowOf(characters)

        val result = getCharactersUseCase.invoke().toList()

        assertThat(result).isEqualTo(listOf(characters))
    }
}