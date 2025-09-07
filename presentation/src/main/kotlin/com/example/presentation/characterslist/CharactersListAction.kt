package com.example.presentation.characterslist

import com.example.presentation.characterslist.model.CharacterUI
import com.example.presentation.core.BaseAction

internal sealed class CharactersListAction : BaseAction {

    data object ShowLoader : CharactersListAction()

    data class ShowCharacters(val characters: List<CharacterUI>) : CharactersListAction()
}