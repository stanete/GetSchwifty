package com.stanete.schwifty.core.platform

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.stanete.schwifty.core.Failure
import kotlinx.coroutines.experimental.Job

abstract class BaseViewModel : ViewModel() {

  var failure: MutableLiveData<Failure> = MutableLiveData()

  // The parent of any coroutine.
  internal val job: Job = Job()

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }

  protected fun handleFailure(failure: Failure) {
    this.failure.value = failure
  }
}