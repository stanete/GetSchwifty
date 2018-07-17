package com.stanete.schwifty.features.character

import android.arch.lifecycle.MutableLiveData
import com.stanete.schwifty.core.coroutine.ContextProvider
import com.stanete.schwifty.core.entity.Character
import com.stanete.schwifty.core.platform.BaseViewModel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

class CharacterViewModel(
  private val id: Int,
  private val getCharacter: GetCharacter,
  private val contextProvider: ContextProvider
) : BaseViewModel() {

  val character = MutableLiveData<Character>()

  fun loadCharacter() = launch(contextProvider.MAIN) {
    withContext(contextProvider.MAIN) { getCharacter(id) }.fold(::handleFailure, ::handleSuccess)
  }

  private fun handleSuccess(character: Character) {
    this.character.value = character
  }
}