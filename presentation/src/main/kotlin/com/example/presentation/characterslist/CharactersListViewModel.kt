package com.example.presentation.characterslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCharactersUseCase
import com.example.presentation.characterslist.mapper.CharacterMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterMapper: CharacterMapper,
) : ViewModel() {

    private val viewModelState = MutableStateFlow<CharactersListUIState>(
        value = CharactersListUIState.Loading
    )
    val uiState = viewModelState.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelState.update { CharactersListUIState.Loading }
        viewModelScope.launch {
            val characters = getCharactersUseCase.invoke()
            viewModelState.update {
                CharactersListUIState.Success(
                    characters = characterMapper.toUIModel(characters)
                )
            }
        }
    }
}