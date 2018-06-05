package com.stanete.schwifty.core.entity

import android.net.Uri

data class Characters(
  val count: Int = 0,
  val next: String? = null,
  val previous: String? = null,
  val results: List<Character>
) {

  fun nextPage(): Int = Uri.parse(next).getQueryParameter("page")?.toInt() ?: 1

}
