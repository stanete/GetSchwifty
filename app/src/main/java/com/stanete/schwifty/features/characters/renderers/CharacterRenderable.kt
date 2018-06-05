package com.stanete.schwifty.features.characters.renderers

import com.stanete.schwifty.core.renderer.Renderable
import com.stanete.schwifty.features.Character

class CharacterRenderable(val character: Character) : Renderable {
  override val spanSize: Int
    get() = 1
}
