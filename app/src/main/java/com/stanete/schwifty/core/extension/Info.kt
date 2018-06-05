package com.stanete.schwifty.core.extension

import com.stanete.schwifty.features.characters.Info

fun <T> Info.map(function: (Info) -> T): T {
  return function(this)
}
