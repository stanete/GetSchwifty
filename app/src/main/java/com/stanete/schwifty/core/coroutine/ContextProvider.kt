package com.stanete.schwifty.core.coroutine

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class ContextProvider {
  open val MAIN: CoroutineContext by lazy { UI }
  open val IO: CoroutineContext by lazy { CommonPool }
}
