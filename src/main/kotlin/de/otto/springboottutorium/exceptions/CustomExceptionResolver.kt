package de.otto.springboottutorium.exceptions

import de.otto.springboottutorium.dto.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionResolver {

    @ExceptionHandler(ServiceException::class)
    fun handleServiceException(ex: ServiceException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(ex.message), ex.httpCode);
    }
}