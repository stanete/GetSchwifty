package com.stanete.schwifty

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.apache.commons.io.FileUtils
import org.testng.annotations.AfterTest
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.io.IOException
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

abstract class HttpStubbingTest {

  private val server = MockWebServer()

  private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
      })
      .build()

  // Retrofit needs to be initialized after the server starts.
  private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(server.url("/").toString())
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
  }

  @BeforeTest
  open fun setUp() {
    // Start the server.
    server.start()
  }

  @AfterTest
  @Throws(IOException::class)
  open fun tearDown() {
    // Shut down the server. Instances cannot be reused.
    server.shutdown()
  }

  fun <T> create(service: Class<T>): T = retrofit.create(service)

  @Throws(IOException::class)
  fun enqueueMockResponse(code: Int) {
    val mockResponse = MockResponse()
    mockResponse.setResponseCode(code)
    server.enqueue(mockResponse)
  }

  @Throws(IOException::class)
  fun enqueueMockResponse(
    code: Int,
    responseFileName: String
  ) {
    val mockResponse = MockResponse()
    val json = getJsonFrom(responseFileName)

    mockResponse.setResponseCode(code)
    mockResponse.setBody(json)

    server.enqueue(mockResponse)
  }

  @Throws(InterruptedException::class)
  fun assertRequestSentTo(url: String) {
    val request = server.takeRequest()
    assertEquals(expected = url, actual = request.path)
  }

  @Throws(InterruptedException::class)
  fun assertGetRequestSentTo(url: String) {
    val request = server.takeRequest()
    assertEquals(url, request.path)
    assertEquals(expected = "GET", actual = request.method)
  }

  @Throws(InterruptedException::class)
  fun assertPostRequestSentTo(url: String) {
    val request = server.takeRequest()
    assertEquals(url, request.path)
    assertEquals(expected = "POST", actual = request.method)
  }

  @Throws(InterruptedException::class)
  fun assertPutRequestSentTo(url: String) {
    val request = server.takeRequest()
    assertEquals(expected = url, actual = request.path)
    assertEquals(expected = "PUT", actual = request.method)
  }

  @Throws(InterruptedException::class)
  fun assertDeleteRequestSentTo(url: String) {
    val request = server.takeRequest()
    assertEquals(expected = url, actual = request.path)
    assertEquals(expected = "DELETE", actual = request.method)
  }

  @Throws(InterruptedException::class, IOException::class)
  fun assertRequestBodyEquals(fileName: String) {
    val request = server.takeRequest()
    val json = getJsonFrom(fileName)
    assertEquals(expected = json, actual = request.body.readUtf8())
  }

  @Throws(IOException::class)
  private fun getJsonFrom(name: String): String {
    val fileName = javaClass.getResource("/$name")
        .file
    val file = File(fileName)
    val lines = FileUtils.readLines(file, "UTF-8")
    val stringBuilder = StringBuilder()
    for (line in lines) {
      stringBuilder.append(line)
    }
    return stringBuilder.toString()
  }

}