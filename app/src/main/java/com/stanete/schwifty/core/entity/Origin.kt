package com.stanete.schwifty.core.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Origin(
  val id: Int,
  val name: String
)
