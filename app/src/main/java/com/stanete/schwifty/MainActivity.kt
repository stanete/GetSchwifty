package com.stanete.schwifty

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stanete.schwifty.features.characters.CharactersActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    startActivity(Intent(this, CharactersActivity::class.java))
    finish()
  }
}
