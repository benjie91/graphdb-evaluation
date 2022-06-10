package io.graphdb.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GraphApplication

fun main(args: Array<String>) {
    SpringApplication.run(GraphApplication::class.java, *args)
}
