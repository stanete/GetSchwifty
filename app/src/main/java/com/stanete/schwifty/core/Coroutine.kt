package com.stanete.schwifty.core

import com.stanete.schwifty.core.CoroutineContext.IO
import com.stanete.schwifty.core.CoroutineContext.MAIN
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Static object that holds the [CoroutineContext]s of the coroutines.
 */
object CoroutineContext {
  var MAIN: CoroutineContext = UI
  var IO: CoroutineContext = CommonPool
}

/**
 * Launches new coroutine in the [MAIN] thread without blocking current thread and returns a
 * reference to the coroutine as a [Job].
 */
fun launch(block: suspend CoroutineScope.() -> Unit) = launch(context = MAIN, block = block)

/**
 * Launches new coroutine in the [MAIN] thread without blocking current thread and returns a
 * reference to the coroutine as a [Job].
 *
 * Additionally the [BaseViewModel] will cancel the coroutine in it's onCleared function.
 */
fun BaseViewModel.launch(block: suspend CoroutineScope.() -> Unit) =
  launch(MAIN, parent = job, block = block)

/**
 * Creates new coroutine that will run in the [IO] context and returns its future result as an
 * implementation of [Deferred].
 */
fun <T> async(block: suspend CoroutineScope.() -> T) = async(context = IO, block = block)
