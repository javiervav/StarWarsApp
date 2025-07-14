package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.ui.theme.StarWarsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarWarsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            StarWarsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StarWarsNavGraph(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}