package com.example.videothek.validation.annotations

import com.example.videothek.validation.validators.GenreValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [GenreValidator::class])
annotation class ValidGenre (
    val message: String = "Invalid Genre",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)