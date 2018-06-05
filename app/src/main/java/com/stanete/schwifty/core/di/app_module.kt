package com.stanete.schwifty.core.di

import com.stanete.schwifty.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule: Module = applicationContext {

  bean { createOkHttpClient() }

  bean { createRetrofit(get(), BuildConfig.BASE_URL) }
}

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    })
    .build()

fun createRetrofit(
  okHttpClient: OkHttpClient,
  baseUrl: String
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(MoshiConverterFactory.create())
    .client(okHttpClient)
    .build()
