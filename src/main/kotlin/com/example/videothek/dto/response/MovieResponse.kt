package com.example.videothek.dto.response

import com.example.videothek.model.Movie

data class MovieResponse(
    val movie: Movie
) : OpenResponse()