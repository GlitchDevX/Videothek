package de.otto.springboottutorium.model

import jakarta.validation.constraints.NotNull

data class Filter (
    @NotNull
    val filterType: FilterType?,

    val filterValue: String?,
)