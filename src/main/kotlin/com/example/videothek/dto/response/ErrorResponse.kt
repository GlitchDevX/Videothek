package com.example.videothek.dto.response

data class ErrorResponse(
    val error: String
) : OpenResponse()