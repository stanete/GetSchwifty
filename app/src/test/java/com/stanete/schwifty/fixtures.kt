package com.stanete.schwifty

import com.stanete.schwifty.core.entity.Character
import com.stanete.schwifty.core.entity.Characters
import com.stanete.schwifty.core.entity.Origin

fun someCharacters(): Characters = Characters(
    count = 12,
    next = "http://schwiftydjango.herokuapp.com/characters/?page=2",
    previous = null,
    results = listOf(
        Character(
            id = 2,
            name = "Birdperson",
            origin = Origin(
                id = 7,
                name = "Planet Squanch"
            ),
            image = "https://schwifty-media.s3.amazonaws.com/media/characters/birdperson.jpeg"
        ),
        Character(
            id = 1,
            name = "Rick Sánchez",
            origin = Origin(
                id = 2,
                name = "Earth (C-137)"
            ),
            image = "https://schwifty-media.s3.amazonaws.com/media/characters/rick-sanchez.jpeg"
        ),
        Character(
            id = 3,
            name = "Beth Smith",
            origin = Origin(
                id = 1,
                name = "Earth (Replacement Dimension)"
            ),
            image = "https://schwifty-media.s3.amazonaws.com/media/characters/beth-smith.jpeg"
        )
    )
)

fun aCharacter(): Character = Character(
    id = 2,
    name = "Birdperson",
    origin = Origin(
        id = 7,
        name = "Planet Squanch"
    ),
    image = "https://schwifty-media.s3.amazonaws.com/media/characters/birdperson.jpeg"
)
