package com.example.videothek

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootTutoriumApplication

// start db with:
// docker run -d --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=Videothek -e MONGO_INITDB_ROOT_PASSWORD=ZpaEVfrd4QBTRgI0bRxbLgjDP7K1w4 mongo

fun main(args: Array<String>) {
	runApplication<SpringbootTutoriumApplication>(*args)
}
