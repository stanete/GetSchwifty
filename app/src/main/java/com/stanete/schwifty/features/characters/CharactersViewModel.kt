package com.stanete.schwifty.features.characters

import android.arch.lifecycle.MutableLiveData
import com.stanete.schwifty.core.BaseViewModel
import com.stanete.schwifty.core.async
import com.stanete.schwifty.core.fold
import com.stanete.schwifty.core.launch
import com.stanete.schwifty.features.Character

class CharactersViewModel(private val repository: CharactersRepository) : BaseViewModel() {

  val characters = MutableLiveData<Characters>()

  fun loadCharacters() = launch {
    val page: Int = characters.value?.info?.nextPage() ?: 0

    async { repository.getCharacters(page) }.await()
        .fold(::handleFailure, ::handleSuccess)
  }

  fun reloadCharacters() = launch {
    async { repository.getCharacters() }.await()
        .fold(::handleFailure, ::handleSuccess)
  }

  private fun handleSuccess(characters: Characters) {
    val info: Info = characters.info
    val results: MutableList<Character> = mutableListOf()
    results.addAll(this.characters.value?.results.orEmpty())
    results.addAll(characters.results)
    this.characters.value = Characters(info, results)
  }
}
