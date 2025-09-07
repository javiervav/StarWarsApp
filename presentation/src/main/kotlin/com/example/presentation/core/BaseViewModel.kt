package com.example.presentation.core

import androidx.lifecycle.ViewModel

internal open class BaseViewModel<S : BaseAction, T : BaseUIState, U: BaseReducer<S, T>>(
    open val reducer: U
) : ViewModel() {

    internal fun handleAction(action: S, currentState: T): T =
        reducer.apply(action, currentState)
}