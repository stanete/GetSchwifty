package com.stanete.schwifty.features.characters

import com.stanete.schwifty.R
import com.stanete.schwifty.core.renderer.Renderer

class LoadingRenderer(private val viewModel: CharactersViewModel) : Renderer<LoadingRenderable>() {

  override val layoutId: Int
    get() = R.layout.renderer_loading

  override fun render() {
    viewModel.loadCharacters()
  }
}
