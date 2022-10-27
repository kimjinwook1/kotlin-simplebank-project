package com.kotlin.simplebankapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SimplebankAppApplication

fun main(args: Array<String>) {
    runApplication<SimplebankAppApplication>(*args)
}
