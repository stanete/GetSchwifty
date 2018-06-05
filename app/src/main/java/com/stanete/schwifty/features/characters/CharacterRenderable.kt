package com.stanete.schwifty.features.characters

import com.stanete.schwifty.core.entity.Character
import com.stanete.schwifty.core.renderer.Renderable

class CharacterRenderable(val character: Character) : Renderable {
  override val spanSize: Int
    get() = 1
}
