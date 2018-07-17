package com.stanete.schwifty

import arrow.core.Right
import com.stanete.schwifty.features.characters.CharactersRepository
import com.stanete.schwifty.features.characters.CharactersViewModel
import kotlinx.coroutines.experimental.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class CharactersViewModelTest : BaseViewModelTest() {

  private val repository = mock(CharactersRepository::class.java)
  private val viewModel = CharactersViewModel(repository, TestContextProvider())

  @Test
  fun `updates live data when loading characters`() = runBlocking {
    whenever(repository.getCharacters(anyInt())).thenReturn(Right(someCharacters()))

    viewModel.loadCharacters()

    assertEquals(expected = someCharacters(), actual = viewModel.characters.value)
  }

  @Test
  fun `updates live data when reloading characters`() = runBlocking {
    whenever(repository.getCharacters(anyInt())).thenReturn(Right(someCharacters()))

    viewModel.reloadCharacters()

    assertEquals(expected = someCharacters(), actual = viewModel.characters.value)
  }
}
