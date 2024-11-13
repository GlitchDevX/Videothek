package de.otto.springboottutorium.model

import java.util.*

data class Rating(
    val id: UUID,
    val movieId: UUID,
    val rating: Double,
)
