package com.example.videothek.exceptions

import org.springframework.http.HttpStatus

abstract class ServiceException(override val message: String, val httpCode: HttpStatus) : RuntimeException(message);

class NotFoundException(message: String) : ServiceException(message, HttpStatus.NOT_FOUND);
class IllegalArgumentException(message: String) : ServiceException(message, HttpStatus.BAD_REQUEST);
class NotFreeException(message: String) : ServiceException(message, HttpStatus.CONFLICT);