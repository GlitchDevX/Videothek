package de.otto.springboottutorium.dto.response

import de.otto.springboottutorium.model.Movie

data class MovieResponse(
    val movie: Movie
) : OpenResponse()