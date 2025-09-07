package com.example.presentation.characterslist

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCharactersUseCase
import com.example.presentation.characterslist.mapper.CharacterMapper
import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CharactersListViewModel @Inject constructor(
    override val reducer: CharactersListReducer,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterMapper: CharacterMapper,
) : BaseViewModel<CharactersListAction, CharactersListUIState, CharactersListReducer>(reducer) {

    init {
        getCharacters()
    }

    override fun getInitialState(): CharactersListUIState =
        CharactersListUIState.Loading

    private fun getCharacters() {
        updateState(action = CharactersListAction.ShowLoader)
        viewModelScope.launch {
            val characters = getCharactersUseCase.invoke()
            updateState(
                action = CharactersListAction.ShowCharacters(
                    characters = characterMapper.toUIModel(characters)
                ),
            )
        }
    }
}