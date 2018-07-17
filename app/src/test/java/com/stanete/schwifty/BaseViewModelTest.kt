package com.stanete.schwifty

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule

abstract class BaseViewModelTest {

  /**
   * This rule will make sure that the writing of the value of any live data will be synchronous
   * instead of asynchronous.
   */
  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()
}
