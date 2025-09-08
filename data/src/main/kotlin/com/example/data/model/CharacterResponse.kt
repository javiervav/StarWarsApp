package com.example.data.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class CharacterResponse(
    @SerialName("name") val name: String,
    val imageUrl: String? = null,
)
