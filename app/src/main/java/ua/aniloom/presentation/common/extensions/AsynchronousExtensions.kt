package ua.aniloom.presentation.common.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * [launch] coroutine with [repeatOnLifecycle] API
 *
 * @param state [Lifecycle.State][androidx.lifecycle.Lifecycle.State] in which `block` runs in a new coroutine. That coroutine
 * will cancel if the lifecycle falls below that state, and will restart if it's in that state
 * again.
 * @param block The block to run when the lifecycle is at least in [state] state.
 */
inline fun LifecycleOwner.launchWithRepeatOnLifecycle(
    state: Lifecycle.State,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            block()
        }
    }
}

/**
 * [collect] flow safely with [launchWithRepeatOnLifecycle]
 */
inline fun <T> Flow<T>.launchAndCollectIn(
    viewLifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline collector: suspend CoroutineScope.(T) -> Unit
) = viewLifecycleOwner.launchWithRepeatOnLifecycle(state) {
    collect { collector(it) }
}

/**
 * [collectLatest] flow safely with [launchWithRepeatOnLifecycle]
 */
inline fun <T> Flow<T>.launchAndCollectLatestIn(
    viewLifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline collector: suspend CoroutineScope.(T) -> Unit
) = viewLifecycleOwner.launchWithRepeatOnLifecycle(state) {
    collectLatest { collector(it) }
}

inline fun <T : Any, R : Any> Flow<PagingData<T>>.mapPaging(
    coroutineScope: CoroutineScope,
    crossinline transform: (value: T) -> R
): Flow<PagingData<R>> = this.map { value: PagingData<T> ->
    value.map {
        it.let(transform)
    }
}.cachedIn(coroutineScope)