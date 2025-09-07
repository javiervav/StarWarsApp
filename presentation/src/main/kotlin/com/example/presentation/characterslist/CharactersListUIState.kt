package com.example.presentation.characterslist

import com.example.presentation.characterslist.model.CharacterUI

internal sealed class CharactersListUIState {

    data object Loading : CharactersListUIState()

    data class Success(
        val characters: List<CharacterUI>
    ) : CharactersListUIState()

    data object Error : CharactersListUIState()
}