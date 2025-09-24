package com.example.domain

import com.example.domain.model.Character

const val CHARACTER_NAME = "John Doe"
const val CHARACTER_IMAGE_URL = "https://starwars/images/Luke.jpg"

internal fun getCharacter(
    name: String = CHARACTER_NAME,
    imageUrl: String? = CHARACTER_IMAGE_URL,
) = Character(
    name = name,
    imageUrl = imageUrl,
)