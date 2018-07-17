package com.stanete.schwifty.features.character

import arrow.core.Either
import com.stanete.schwifty.core.Failure
import com.stanete.schwifty.core.entity.Character
import com.stanete.schwifty.features.characters.CharactersRepository

class GetCharacter(private val repository: CharactersRepository) {

  operator fun invoke(id: Int): Either<Failure, Character> = repository.getCharacter(id)

}