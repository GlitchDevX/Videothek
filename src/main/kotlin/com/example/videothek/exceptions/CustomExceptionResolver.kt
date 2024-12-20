package com.example.videothek.exceptions

import com.example.videothek.dto.response.ErrorResponse
import jakarta.ws.rs.ForbiddenException
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class CustomExceptionResolver {

    @ExceptionHandler(ServiceException::class)
    fun handleServiceException(ex: ServiceException, re: WebRequest): Any {
        return if (re.getHeader(HttpHeaders.ACCEPT) != null && re.getHeader(HttpHeaders.ACCEPT)!!.contains(MediaType.TEXT_HTML_VALUE))
            handleServiceExceptionUi(ex);
        else
            ResponseEntity(ErrorResponse(ex.message), ex.httpCode);
    }

    fun handleServiceExceptionUi(ex: ServiceException): ModelAndView {
        val model = ModelAndView("errors/ServiceException")
        model.addObject("message", ex.message)
        model.addObject("code", ex.httpCode.toString())
        return model;
    }

    @ExceptionHandler(Exception::class)
    fun handleForbiddenException(ex: Exception): ModelAndView {
        val model = ModelAndView("errors/ServiceException")
        model.addObject("message", ex.message);
        model.addObject("code", "Unknown Error")
        return model;
    }
}