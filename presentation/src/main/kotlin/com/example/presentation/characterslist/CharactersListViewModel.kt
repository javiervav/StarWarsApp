package com.example.presentation.characterslist

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCharactersUseCase
import com.example.presentation.characterslist.mapper.CharacterMapper
import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CharactersListViewModel @Inject constructor(
    override val reducer: CharactersListReducer,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterMapper: CharacterMapper,
) : BaseViewModel<CharactersListAction, CharactersListUIState, CharactersListReducer>(reducer) {

    private val viewModelState = MutableStateFlow<CharactersListUIState>(
        value = CharactersListUIState.Loading
    )
    val uiState = viewModelState.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelState.update {
            handleAction(
                action = CharactersListAction.ShowLoader,
                currentState = viewModelState.value
            )
        }
        viewModelScope.launch {
            val characters = getCharactersUseCase.invoke()
            viewModelState.update {
                handleAction(
                    action = CharactersListAction.ShowCharacters(
                        characters = characterMapper.toUIModel(characters)
                    ),
                    currentState = viewModelState.value
                )
            }
        }
    }
}