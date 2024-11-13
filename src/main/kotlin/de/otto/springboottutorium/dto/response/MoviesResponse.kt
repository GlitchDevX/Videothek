package de.otto.springboottutorium.dto.response

import de.otto.springboottutorium.model.Movie

data class MoviesResponse(
    val movies: List<Movie>
) : OpenResponse()