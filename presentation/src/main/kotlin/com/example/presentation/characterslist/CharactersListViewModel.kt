package com.example.presentation.characterslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            val characters = getCharactersUseCase.invoke()
            print(characters)
        }
    }
}