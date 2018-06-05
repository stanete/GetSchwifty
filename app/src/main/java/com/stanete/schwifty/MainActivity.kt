package com.stanete.schwifty

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stanete.schwifty.features.characters.startCharactersActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    startCharactersActivity()
    finish()
  }
}
