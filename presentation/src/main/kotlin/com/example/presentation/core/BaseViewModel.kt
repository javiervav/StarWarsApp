package com.example.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal abstract class BaseViewModel<S : BaseAction, T : BaseUIState, U: BaseReducer<S, T>>(
    open val reducer: U
) : ViewModel() {

    private val viewModelState = MutableStateFlow<T>(
        value = getInitialState()
    )
    val uiState = viewModelState.asStateFlow()

    abstract fun getInitialState(): T

    protected fun updateState(action: S) {
        viewModelState.update {
            reducer.apply(action, viewModelState.value)
        }
    }
}