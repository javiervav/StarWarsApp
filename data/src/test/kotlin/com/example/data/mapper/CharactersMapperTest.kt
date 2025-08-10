package com.example.data.mapper

import com.example.data.getCharacter
import com.example.data.getCharacterResponse
import com.example.data.mapper.CharactersMapper.toDomainModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CharactersMapperTest {

    @Test
    fun `toDomainModel function in list of CharacterResponse should parse correctly`() {
        val charactersResponse = listOf(
            getCharacterResponse("John Doe"),
            getCharacterResponse("Jane Doe"),
        )
        val expectedCharacters = listOf(
            getCharacter("John Doe"),
            getCharacter("Jane Doe"),
        )

        val result = charactersResponse.toDomainModel()

        assertThat(result).isEqualTo(expectedCharacters)
    }

    @Test
    fun `toDomainModel function in CharacterResponse should parse correctly`() {
        val expectedCharacter = getCharacter()

        val result = getCharacterResponse().toDomainModel()

        assertThat(result).isEqualTo(expectedCharacter)
    }
}