package com.stanete.schwifty.features.characters

import android.net.Uri

data class Info(
  val count: Int,
  val pages: Int,
  val next: String,
  val prev: String
) {

  fun nextPage(): Int = Uri.parse(next)
      .getQueryParameter("page")
      .toInt()

}
