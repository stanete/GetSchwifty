package com.stanete.schwifty.core.extension

import android.support.v7.widget.GridLayoutManager

fun GridLayoutManager.spanSizeLookup(block: (position: Int) -> Int) {
  spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int = block.invoke(position)
  }
}