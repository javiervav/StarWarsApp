package com.example.data.mapper

import com.example.data.getCharacter
import com.example.data.getCharacterResponse
import com.example.data.mapper.CharactersMapper.toDomainModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CharactersMapperTest {

    @Test
    fun `toDomainModel function in flow of CharacterResponses should parse correctly`() = runTest {
            val charactersResponse = listOf(
                getCharacterResponse("John Doe", "imageUrl1"),
                getCharacterResponse("Jane Doe", "imageUrl2"),
            )
            val flowResponse = flowOf(charactersResponse)
            val expectedCharacters = listOf(
                getCharacter("John Doe", "imageUrl1"),
                getCharacter("Jane Doe", "imageUrl2"),
            )

            val result = flowResponse.toDomainModel()

            assertThat(result.first()).isEqualTo(expectedCharacters)
        }

    @Test
    fun `toDomainModel function in CharacterResponse should parse correctly`() {
        val expectedCharacter = getCharacter()

        val result = getCharacterResponse().toDomainModel()

        assertThat(result).isEqualTo(expectedCharacter)
    }
}