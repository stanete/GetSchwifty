package com.stanete.schwifty.core.di

import com.stanete.schwifty.core.di.Params.CHARACTER_ID
import com.stanete.schwifty.features.character.CharacterViewModel
import com.stanete.schwifty.features.characters.CharactersRepository
import com.stanete.schwifty.features.characters.CharactersService
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val characterModule: Module = applicationContext {

  factory { createService<CharactersService>(get()) }

  factory { CharactersRepository(get()) }

  viewModel { params -> CharacterViewModel(params[CHARACTER_ID], get(), get()) }

}

object Params {
  const val CHARACTER_ID = "CHARACTER_ID"
}