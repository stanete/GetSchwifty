package com.stanete.schwifty.features

data class Character(
  val id: Int,
  val name: String,
  val status: String,
  val species: String,
  val type: String,
  val gender: String,
  val origin: Origin,
  val location: Location,
  val image: String
)
