package com.example.videothek.dto.request

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty

@Suppress("ArrayInDataClass")
data class MoviesRequest (
    @NotEmpty
    @Valid
    val movies: Array<MovieRequest>
)