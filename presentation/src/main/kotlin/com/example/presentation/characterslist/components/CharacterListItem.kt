package com.example.presentation.characterslist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.presentation.characterslist.model.CharacterUI
import com.example.ui.R as uiR

@Composable
internal fun CharacterListItem(character: CharacterUI) {
    val painter = rememberAsyncImagePainter(
        model = character.imageUrl,
        placeholder = painterResource(uiR.drawable.character_placeholder),
        error = painterResource(uiR.drawable.character_placeholder)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
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