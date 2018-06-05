package com.stanete.schwifty.core.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

inline fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(
  liveData: L,
  crossinline block: (t: T) -> Unit
) = liveData.observe(this, Observer { it?.let(block) })
