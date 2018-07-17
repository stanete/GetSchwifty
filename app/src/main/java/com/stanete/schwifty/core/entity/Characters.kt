package com.stanete.schwifty.core.entity

import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLDecoder

data class Characters(
  val count: Int = 0,
  val next: String? = null,
  val previous: String? = null,
  val results: List<Character>
) {

  fun nextPage(): Int = next?.let { getQueryParameters(it) }?.get("page")?.toInt() ?: 1

  @Throws(UnsupportedEncodingException::class)
  private fun getQueryParameters(url: String): Map<String, String> {

    val queryPairs = LinkedHashMap<String, String>()
    val query = URL(url).query
    val pairs = query.split("&".toRegex())
        .dropLastWhile({ it.isEmpty() })
        .toTypedArray()

    val encoding = "UTF-8"
    for (pair in pairs) {
      val index = pair.indexOf("=")
      queryPairs[URLDecoder.decode(pair.substring(0, index), encoding)] =
          URLDecoder.decode(pair.substring(index + 1), encoding)
    }
    return queryPairs
  }
}