package com.example.presentation

import com.example.domain.model.Character
import com.example.presentation.characterslist.model.CharacterUI

const val CHARACTER_NAME = "John Doe"
const val CHARACTER_IMAGE_URL = "https://starwars/images/Luke.jpg"

internal fun getCharacter(
    name: String = CHARACTER_NAME,
    imageUrl: String? = CHARACTER_IMAGE_URL,
) = Character(
    name = name,
    imageUrl = imageUrl,
)

internal fun getCharacterUI(
    name: String = CHARACTER_NAME,
    imageUrl: String? = CHARACTER_IMAGE_URL,
) = CharacterUI(
    name = name,
    imageUrl = imageUrl,
)