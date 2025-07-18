package com.example.data.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class CharacterResponseWrapper(
    @SerialName("count") val totalAmount: Int,
    @SerialName("previous") val previousCharactersUrl: String?,
    @SerialName("next") val nextCharactersUrl: String?,
    @SerialName("results") val characters: List<CharacterResponse>,
)