package com.example.presentation.characterslist.mapper

import com.example.presentation.getCharacter
import com.example.presentation.getCharacterUI
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CharacterMapperImplTest {

    private val characterMapper = CharacterMapperImpl()

    @Test
    fun `toUIModel should return correct list of CharacterUI`() {
        val characters = listOf(
            getCharacter(name = "Luke Skywalker", imageUrl = "https://starwars/images/Luke.jpg"),
            getCharacter(name = "Darth Vader", imageUrl = "https://starwars/images/Vader.jpg"),
        )
        val expected = listOf(
            getCharacterUI(name = "Luke Skywalker", imageUrl = "https://starwars/images/Luke.jpg"),
            getCharacterUI(name = "Darth Vader", imageUrl = "https://starwars/images/Vader.jpg"),
        )

        val result = characterMapper.toUIModel(characters)

        assertThat(result).isEqualTo(expected)
    }
}