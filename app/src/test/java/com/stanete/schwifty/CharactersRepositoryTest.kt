package com.stanete.schwifty

import arrow.core.Right
import com.stanete.schwifty.features.characters.CharactersRepository
import com.stanete.schwifty.features.characters.CharactersService
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CharactersRepositoryTest : HttpStubbingTest() {

  private lateinit var service: CharactersService
  private lateinit var repository: CharactersRepository

  @BeforeTest
  override fun setUp() {
    super.setUp()
    service = create(CharactersService::class.java)
    repository = CharactersRepository(service)
  }

  @Test
  fun `sends a GET request to the correct endpoint when getting characters`() {
    enqueueMockResponse(200, "getCharactersResponse.json")

    repository.getCharacters()

    assertGetRequestSentTo("")
  }

  @Test
  fun `sends a GET request to the correct endpoint when getting a character`() {
    enqueueMockResponse(200, "getCharacterResponse.json")

    repository.getCharacter(2)

    assertGetRequestSentTo("/characters/2")
  }

  @Test
  fun `returns Left when the api responds with a 500`() {
    enqueueMockResponse(500)

    val either = repository.getCharacters()

    assertTrue(either.isLeft())
  }

  @Test
  fun `returns Right with some characters when the api responds with a 200`() {
    enqueueMockResponse(200, "getCharactersResponse.json")

    val either = repository.getCharacters()

    assertTrue(either.isRight())
    assertEquals(expected = Right(someCharacters()), actual = either)
  }

  @Test
  fun `returns Right with a character when the api responds with a 200`() {
    enqueueMockResponse(200, "getCharacterResponse.json")

    val either = repository.getCharacter(2)

    assertTrue(either.isRight())
    assertEquals(expected = Right(aCharacter()), actual = either)
  }
}


