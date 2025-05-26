package com.example.presentation.characterslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CharactersListScreen(
    modifier: Modifier
) {
    Text(
        modifier = modifier.fillMaxSize(),
        text = "Hello World"
    )
}

@Preview(showBackground = true)
@Composable
fun CharactersListScreenPreview() {
    CharactersListScreen(modifier = Modifier)
}