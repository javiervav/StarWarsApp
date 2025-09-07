package com.example.presentation.core

internal interface BaseReducer<S : BaseAction, T : BaseUIState> {

    fun apply(action: S, uiState: T): T
}