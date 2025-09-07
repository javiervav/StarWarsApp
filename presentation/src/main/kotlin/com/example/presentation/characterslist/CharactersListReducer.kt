package com.example.presentation.characterslist

import com.example.presentation.core.BaseReducer
import javax.inject.Inject

internal class CharactersListReducer @Inject constructor() :
    BaseReducer<CharactersListAction, CharactersListUIState> {

    override fun apply(
        action: CharactersListAction,
        uiState: CharactersListUIState
    ): CharactersListUIState = when (action) {

        is CharactersListAction.ShowLoader -> CharactersListUIState.Loading

        is CharactersListAction.ShowCharacters -> CharactersListUIState.Success(
            characters = action.characters
        )
    }
}