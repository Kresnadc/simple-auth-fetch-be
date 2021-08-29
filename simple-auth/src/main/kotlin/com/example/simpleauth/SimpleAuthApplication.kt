package com.example.simpleauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleAuthApplication

fun main(args: Array<String>) {
	runApplication<SimpleAuthApplication>(*args)
}
