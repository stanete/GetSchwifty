package com.stanete.schwifty

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.stanete.schwifty.core.di.appModule
import com.stanete.schwifty.core.di.charactersModule
import org.koin.android.ext.android.startKoin

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin(this, listOf(appModule, charactersModule))
    Fresco.initialize(this)
  }
}
