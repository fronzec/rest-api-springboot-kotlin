package com.example.kotlindemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Entry point for application
 */
@SpringBootApplication
class KotlinDemoApplication

fun main(args: Array<String>) {
    runApplication<KotlinDemoApplication>(*args)
}
