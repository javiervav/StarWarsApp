package com.example.presentation.characterslist

import com.example.domain.usecase.GetCharactersUseCase
import com.example.presentation.characterslist.mapper.CharacterMapper
import com.example.presentation.getCharacter
import com.example.presentation.getCharacterUI
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CharactersListViewModelTest {

    private val charactersListReducer: CharactersListReducer = mockk()
    private val getCharactersUseCase: GetCharactersUseCase = mockk()
    private val characterMapper: CharacterMapper = mockk()
    private val charactersListViewModel = CharactersListViewModel(
        reducer = charactersListReducer,
        getCharactersUseCase = getCharactersUseCase,
        characterMapper = characterMapper
    )

    @Test
    fun `getInitialState should return correct initial state`() {
        val result = charactersListViewModel.getInitialState()

        assertThat(result).isEqualTo(CharactersListUIState.Loading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getCharacters should update state correctly`() = runTest {
        every {
            charactersListReducer.apply(CharactersListAction.ShowLoader, any())
        } returns CharactersListUIState.Loading
        val characters = listOf(getCharacter("John Doe"), getCharacter("Jane Doe"))
        coEvery { getCharactersUseCase.invoke() } returns flowOf(characters)
        val charactersUI = listOf(getCharacterUI("John Doe"), getCharacterUI("Jane Doe"))
        every { characterMapper.toUIModel(any()) } returns charactersUI
        every {
            charactersListReducer.apply(CharactersListAction.ShowCharacters(charactersUI), any())
        } returns CharactersListUIState.Loading

        charactersListViewModel.getCharacters()
        advanceUntilIdle() // Wait for all coroutines to finish

        verify(exactly = 1) {
            charactersListReducer.apply(
                action = CharactersListAction.ShowLoader,
                uiState = charactersListViewModel.uiState.value
            )
        }
        verify(exactly = 1) { getCharactersUseCase.invoke() }
        verify(exactly = 1) { characterMapper.toUIModel(characters) }
        verify(exactly = 1) {
            charactersListReducer.apply(
                action = CharactersListAction.ShowCharacters(charactersUI),
                uiState = charactersListViewModel.uiState.value
            )
            }
    }
}