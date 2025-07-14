package com.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.characterslist.CharactersListScreen

@Composable
fun StarWarsNavGraph(
    modifier: Modifier,
) {
    val navHostController: NavHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = CharactersList,
    ) {
        composable<CharactersList> {
            CharactersListScreen(
                modifier = modifier,
            )
        }
    }
}