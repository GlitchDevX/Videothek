package com.example.videothek.model

import jakarta.validation.constraints.NotNull

data class Filter (
    @NotNull
    val filterType: com.example.videothek.model.FilterType?,

    val filterValue: String?,
)