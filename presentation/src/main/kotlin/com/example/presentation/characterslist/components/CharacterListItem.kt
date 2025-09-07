package com.example.presentation.characterslist.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.characterslist.model.CharacterUI

@Composable
internal fun CharacterListItem(character: CharacterUI) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(text = character.name)
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterListItemPreview() {
    CharacterListItem(
        character = CharacterUI(
            name = "John Doe",
        ),
    )
}