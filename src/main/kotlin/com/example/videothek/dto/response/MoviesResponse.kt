package com.example.videothek.dto.response

import com.example.videothek.model.Movie

data class MoviesResponse(
    val movies: List<Movie>
) : OpenResponse()