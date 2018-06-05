package com.stanete.schwifty.core.di

import com.stanete.schwifty.features.characters.CharactersRepository
import com.stanete.schwifty.features.characters.CharactersService
import com.stanete.schwifty.features.characters.CharactersViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val charactersModule: Module = applicationContext {

  factory { createService<CharactersService>(get()) }

  factory { CharactersRepository(get()) }

  viewModel { CharactersViewModel(get()) }

}
