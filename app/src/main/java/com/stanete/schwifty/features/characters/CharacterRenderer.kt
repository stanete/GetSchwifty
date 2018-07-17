package com.stanete.schwifty.features.characters

import com.stanete.schwifty.R
import com.stanete.schwifty.core.extension.onClick
import com.stanete.schwifty.core.renderer.Renderer
import com.stanete.schwifty.features.character.startCharacterActivity
import kotlinx.android.synthetic.main.renderer_character.sdvImage
import kotlinx.android.synthetic.main.renderer_character.tvName

class CharacterRenderer : Renderer<CharacterRenderable>() {

  override val layoutId: Int
    get() = R.layout.renderer_character

  override fun render() {
    tvName.text = content.character.name
    sdvImage.setImageURI(content.character.image)
    hookListeners()
  }

  private fun hookListeners() {
    rootView.onClick { context.startCharacterActivity(content.character.id) }
  }
}
