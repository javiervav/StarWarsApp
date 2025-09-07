package com.example.presentation.characterslist.mapper

import com.example.domain.model.Character
import com.example.presentation.characterslist.model.CharacterUI

internal interface CharacterMapper {

    fun toUIModel(characters: List<Character>): List<CharacterUI>
}