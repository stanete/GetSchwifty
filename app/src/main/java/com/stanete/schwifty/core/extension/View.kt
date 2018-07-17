package com.stanete.schwifty.core.extension

import android.view.View

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.invisible() {
  this.visibility = View.GONE
}

infix fun View.onClick(block: (View) -> Unit) {
  this.setOnClickListener { block(it) }
}
