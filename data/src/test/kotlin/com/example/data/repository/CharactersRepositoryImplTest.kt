package com.example.data.repository

import com.example.data.getCharacter
import com.example.data.getCharacterResponse
import com.example.data.remote.CharactersRemoteDataSource
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CharactersRepositoryImplTest {

    private val charactersRemoteDataSource: CharactersRemoteDataSource = mockk()
    private val charactersRepository = CharactersRepositoryImpl(
        charactersRemoteDataSource = charactersRemoteDataSource
    )

    @Test
    fun `getCharacters function should return list of characters`() = runTest {
        val charactersResponse = listOf(
            getCharacterResponse("John Doe"),
            getCharacterResponse("Jane Doe"),
        )
        val expected = listOf(
            getCharacter("John Doe"),
            getCharacter("Jane Doe"),
        )
        coEvery { charactersRemoteDataSource.getCharacters() } returns charactersResponse

        val result = charactersRepository.getCharacters()

        assertThat(result).isEqualTo(expected)
    }
}