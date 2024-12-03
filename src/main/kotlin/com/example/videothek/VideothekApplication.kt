package com.example.videothek

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class VideothekApplication

fun main(args: Array<String>) {
	runApplication<VideothekApplication>(*args)
}
