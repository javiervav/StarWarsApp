package com.example.data.repository

import com.example.data.getCharacter
import com.example.data.getCharacterResponse
import com.example.data.remote.CharactersRemoteDataSource
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
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
        coEvery { charactersRemoteDataSource.getCharacters() } returns flowOf(charactersResponse)
        val expected = listOf(
            getCharacter("John Doe"),
            getCharacter("Jane Doe"),
        )

        val result = charactersRepository.getCharacters().toList()

        assertThat(result).isEqualTo(listOf(expected))
        verify(exactly = 1) { charactersRemoteDataSource.getCharacters() }
    }
}