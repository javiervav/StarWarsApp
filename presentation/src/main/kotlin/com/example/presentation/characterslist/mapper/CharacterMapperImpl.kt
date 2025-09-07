package com.example.presentation.characterslist.mapper

import com.example.domain.model.Character
import com.example.presentation.characterslist.model.CharacterUI
import javax.inject.Inject
import kotlin.collections.map

internal class CharacterMapperImpl @Inject constructor() : CharacterMapper {

    override fun toUIModel(characters: List<Character>): List<CharacterUI> {
        return characters.map { character ->
            character.toUIModel()
        }
    }

    private fun Character.toUIModel() = CharacterUI(
        name = this.name,
    )
}