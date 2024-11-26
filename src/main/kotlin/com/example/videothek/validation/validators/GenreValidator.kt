package com.example.videothek.validation.validators

import com.example.videothek.validation.annotations.ValidGenre
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Value

class GenreValidator (
    @Value("\${com.example.videothek.valid-genres}") private val validGenres: Array<String>
) : ConstraintValidator<ValidGenre, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return validGenres.contains(value);
    }
}