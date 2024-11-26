package com.example.videothek.dto.response

import org.springframework.http.HttpStatus
import java.time.Instant
import java.time.format.DateTimeFormatter

open class OpenResponse (
    val timestamp: String = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
    val status: Int = HttpStatus.OK.value(),
)
