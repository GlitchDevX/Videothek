package de.otto.springboottutorium.dto.request

import de.otto.springboottutorium.model.LendStatus
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@Suppress("ArrayInDataClass")
data class MoviesRequest (
    @NotEmpty
    @Valid
    val movies: Array<MovieRequest>
)