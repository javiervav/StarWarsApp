package com.example.presentation.characterslist

import com.example.presentation.characterslist.model.CharacterUI
import com.example.presentation.core.BaseUIState

internal sealed class CharactersListUIState : BaseUIState {

    data object Loading : CharactersListUIState()

    data class Success(
        val characters: List<CharacterUI>
    ) : CharactersListUIState()

    data object Error : CharactersListUIState()
}