package com.stanete.schwifty.features.characters

import com.stanete.schwifty.core.Either
import com.stanete.schwifty.core.Failure
import com.stanete.schwifty.core.Failure.NetworkFailure
import com.stanete.schwifty.core.Failure.ServerFailure
import com.stanete.schwifty.core.Left
import com.stanete.schwifty.core.Right
import java.io.IOException

class CharactersRepository(private val service: CharactersService) {

  fun getCharacters(page: Int = 0): Either<Failure, Characters> = try {

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
