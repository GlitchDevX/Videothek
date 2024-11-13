package de.otto.springboottutorium.dto.response

data class ErrorResponse(
    val error: String
) : OpenResponse()