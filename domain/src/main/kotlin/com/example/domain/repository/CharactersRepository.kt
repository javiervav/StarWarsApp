package com.example.domain.repository

import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    // Why this function returns a Flow of a list of characters instead of a Flow of characters:
    // When retrieving a list of characters, instead of emitting all of them one by one, I think it's more efficient to emit them all at once.
    // This way, we can avoid updating the state too many times in the ViewModel.
    fun getCharacters(): Flow<List<Character>>
}