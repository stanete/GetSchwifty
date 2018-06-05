package com.stanete.schwifty.features.characters

import android.arch.lifecycle.MutableLiveData
import com.stanete.schwifty.core.coroutine.async
import com.stanete.schwifty.core.coroutine.launch
import com.stanete.schwifty.core.entity.Character
import com.stanete.schwifty.core.entity.Characters
import com.stanete.schwifty.core.fold
import com.stanete.schwifty.core.platform.BaseViewModel

class CharactersViewModel(private val repository: CharactersRepository) : BaseViewModel() {

  val characters = MutableLiveData<Characters>()

  fun loadCharacters() = launch {
    val page: Int = characters.value?.nextPage() ?: 1

    async { repository.getCharacters(page) }.await()
        .fold(::handleFailure, ::handleSuccess)
  }

  fun reloadCharacters() {
    characters.value = Characters(results = listOf())
    loadCharacters()
  }

  private fun handleSuccess(characters: Characters) {
    val results: MutableList<Character> = mutableListOf()
    results.addAll(this.characters.value?.results.orEmpty())
    results.addAll(characters.results)
    this.characters.value = characters.copy(results = results)
  }
}
