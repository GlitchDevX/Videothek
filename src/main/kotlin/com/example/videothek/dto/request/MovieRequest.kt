package com.example.videothek.dto.request

import com.example.videothek.model.LendStatus
import com.example.videothek.validation.annotations.ValidGenre
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class MovieRequest (
    @Size(min = 1, max = 200)
    val title: String,

    @NotBlank
    val description: String,

    @ValidGenre
    val genre: String,

    @NotNull
    val lendStatus: LendStatus,

    @Positive
    val minutesLength: Int,

    @Positive
    val releaseYear: Int
)