package com.stanete.schwifty.features.characters

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import com.stanete.schwifty.core.Failure
import com.stanete.schwifty.core.Failure.NetworkFailure
import com.stanete.schwifty.core.Failure.ServerFailure
import com.stanete.schwifty.core.entity.Character
import com.stanete.schwifty.core.entity.Characters
import java.io.IOException

class CharactersRepository(private val service: CharactersService) {

  fun getCharacter(id: Int): Either<Failure, Character> = try {
    
    val call = service.getCharacter(id)
    val response = call.execute()

    if (response.isSuccessful && response.body() != null) {
      // Ignore the !!. A NPE will never be thrown here.
      Right(response.body()!!)
    } else {
      Left(ServerFailure)
    }
  } catch (e: IOException) {
    Left(NetworkFailure)
  }

  fun getCharacters(page: Int = 1): Either<Failure, Characters> = try {

    val call = service.getCharacters(page)
    val response = call.execute()

    if (response.isSuccessful && response.body() != null) {
      // Ignore the !!. A NPE will never be thrown here.
      Right(response.body()!!)
    } else {
      Left(ServerFailure)
    }
  } catch (e: IOException) {
    Left(NetworkFailure)
  }
}
