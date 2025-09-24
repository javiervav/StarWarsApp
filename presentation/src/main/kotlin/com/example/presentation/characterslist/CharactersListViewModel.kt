package com.example.presentation.characterslist

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCharactersUseCase
import com.example.presentation.characterslist.mapper.CharacterMapper
import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CharactersListViewModel @Inject constructor(
    override val reducer: CharactersListReducer,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterMapper: CharacterMapper,
) : BaseViewModel<CharactersListAction, CharactersListUIState, CharactersListReducer>(reducer) {

    override fun getInitialState(): CharactersListUIState =
        CharactersListUIState.Loading

    fun getCharacters() {
        updateState(action = CharactersListAction.ShowLoader)
        viewModelScope.launch {
            getCharactersUseCase.invoke()
                .map { characters ->
                    characterMapper.toUIModel(characters)
                }
                .collect { characters ->
                    updateState(
                        action = CharactersListAction.ShowCharacters(
                            characters = characters
                        )
                    )
                }
        }
    }
}