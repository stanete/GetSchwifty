package com.stanete.schwifty.features.characters

import com.stanete.schwifty.features.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

  @GET("character")
  fun getCharacters(@Query("page") page: Int = 0): Call<Characters>

  @GET("character/{id}")
  fun getCharacter(@Path("id") id: String): Call<Character>
}
