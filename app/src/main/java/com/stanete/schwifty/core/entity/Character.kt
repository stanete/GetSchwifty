package com.stanete.schwifty.core.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
  val id: Int,
  val name: String,
  val origin: Origin,
  val image: String
)
