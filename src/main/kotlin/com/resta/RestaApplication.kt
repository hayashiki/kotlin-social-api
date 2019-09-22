package com.resta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestaApplication

fun main(args: Array<String>) {
    runApplication<RestaApplication>(*args)
}
