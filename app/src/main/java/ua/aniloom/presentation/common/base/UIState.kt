package ua.aniloom.presentation.common.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ua.aniloom.domain.models.core.NetworkError

sealed class UIState<T> {
    /**
     * [Idle] - The default state when there are no active data requests, and the screen has just been opened.
     */
    class Idle<T> : UIState<T>()

    /**
     * [Loading] - The state after sending a data request and waiting for a response.
     */
    class Loading<T> : UIState<T>()

    /**
     * [Error] - The state in case of an error occurring during a data request.
     *
     * @param error The network error associated with the current state.
     * @see NetworkError
     */
    class Error<T>(val error: NetworkError) : UIState<T>()

    /**
     * [Success] - The state when a data request is successful, and data is returned.
     *
     * @param data The data returned as a result of a successful request.
     */
    class Success<T>(val data: T) : UIState<T>()
}

/**
 * Typealias for [StateFlow] with [UIState]
 */
typealias UIStateFlow<T> = StateFlow<UIState<T>>

/**
 * Creates a [MutableStateFlow] with [UIState] and the given initial value [UIState.Idle]
 */
@Suppress("FunctionName")
fun <T> BaseViewModel.MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

/**
 * Reset [MutableUIStateFlow] to [UIState.Idle]
 */
fun <T> MutableStateFlow<UIState<T>>.reset() { this.value = UIState.Idle() }