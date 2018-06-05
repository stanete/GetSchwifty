package com.stanete.schwifty.core.extension

import android.support.v4.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.refreshing() {
  isRefreshing = true
}

fun SwipeRefreshLayout.notRefreshing() {
  isRefreshing = false
}
