package com.example.data

import com.example.data.model.CharacterResponse
import com.example.domain.model.Character

const val CHARACTER_NAME = "John Doe"

fun getCharacterResponse(
    name: String = CHARACTER_NAME
) = CharacterResponse(
    name = name
)

fun getCharacter(
    name: String = CHARACTER_NAME
) = Character(
    name = name,
)