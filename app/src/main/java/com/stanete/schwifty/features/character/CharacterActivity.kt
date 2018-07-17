package com.stanete.schwifty.features.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.stanete.schwifty.R.layout
import com.stanete.schwifty.core.di.Params.CHARACTER_ID
import com.stanete.schwifty.core.entity.Character
import com.stanete.schwifty.core.extension.invisible
import com.stanete.schwifty.core.extension.observe
import com.stanete.schwifty.core.platform.BaseActivity
import com.stanete.schwifty.features.characters.CharactersActivity
import kotlinx.android.synthetic.main.activity_character.progressBar
import org.koin.android.architecture.ext.viewModel

fun Context.startCharacterActivity(id: Int) {
  val intent = Intent(this, CharactersActivity::class.java)
  intent.putExtra(CHARACTER_ID, id)
  startActivity(intent)
}

class CharacterActivity : BaseActivity() {

  private val viewModel by viewModel<CharacterViewModel> {
    mapOf(CHARACTER_ID to intent.extras[CHARACTER_ID])
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_character)
    observe(viewModel.character, ::renderCharacter)
  }

  private fun renderCharacter(character: Character) {
    progressBar.invisible()
  }
}
