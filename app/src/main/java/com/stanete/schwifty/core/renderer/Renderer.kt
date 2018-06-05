package com.stanete.schwifty.core.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrogomez.renderers.Renderer
import kotlinx.android.extensions.LayoutContainer

abstract class Renderer<T> : Renderer<T>(), LayoutContainer {

  override val containerView: View
    get() = rootView

  abstract val layoutId: Int

  override fun inflate(
    inflater: LayoutInflater,
    parent: ViewGroup
  ): View = LayoutInflater.from(parent.context)
      .inflate(layoutId, parent, false)

  override fun hookListeners(rootView: View) {}

  override fun setUpView(rootView: View) {}
}