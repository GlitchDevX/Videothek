package de.otto.springboottutorium.dto.request

import de.otto.springboottutorium.model.LendStatus
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.util.*

data class IdMovieRequest (
    @Valid
    val id: UUID,

    @Size(min = 1, max = 200)
    val title: String,

    @NotBlank
    val description: String,

    @Size(min = 1, max = 50)
    val genre: String,

    @NotNull
    val lendStatus: LendStatus,

    @Positive
    val minutesLength: Int,

    @Positive
    val releaseYear: Int
)