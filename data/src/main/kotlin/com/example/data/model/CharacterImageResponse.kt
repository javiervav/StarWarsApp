package com.example.data.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
internal data class CharacterImageResponse(
    @SerialName("image") val imageUrl: String,
)
