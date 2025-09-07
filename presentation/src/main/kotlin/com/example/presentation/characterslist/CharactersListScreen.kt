package com.example.presentation.characterslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.characterslist.components.CharacterListItem
import com.example.presentation.characterslist.model.CharacterUI

@Composable
internal fun CharactersListScreen(
    modifier: Modifier,
    viewModel: CharactersListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    when (uiState) {
        is CharactersListUIState.Loading -> LoadingScreen(modifier)
        is CharactersListUIState.Success -> SuccessScreen(uiState, modifier)
        is CharactersListUIState.Error -> ErrorScreen(modifier)
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SuccessScreen(
    uiState: CharactersListUIState.Success,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(uiState.characters) { character ->
            CharacterListItem(character)
        }
    }
}

@Composable
private fun ErrorScreen(modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Error")
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    val characters = listOf(
        CharacterUI("John Doe"),
        CharacterUI("Jane Doe"),
    )
    SuccessScreen(
        uiState = CharactersListUIState.Success(characters),
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        modifier = Modifier
    )
}
