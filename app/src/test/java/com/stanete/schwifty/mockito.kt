package com.stanete.schwifty

import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.stubbing.OngoingStubbing

fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)!!

fun <T> any(): T {
  Mockito.any<T>()
  return uninitialized()
}

fun anyInt(): Int = Mockito.anyInt()

inline fun <reified T> mock(): T = mock(T::class.java)

inline fun <reified T> mock(classToMock: Class<T>): T = mock(classToMock)

fun <T> any(type: Class<T>): T {
  Mockito.any<T>(type)
  return uninitialized()
}

private fun <T> uninitialized(): T = null as T