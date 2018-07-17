package com.stanete.schwifty

import com.stanete.schwifty.core.coroutine.ContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : ContextProvider() {
  override val MAIN: CoroutineContext = Unconfined
  override val IO: CoroutineContext = Unconfined
}
