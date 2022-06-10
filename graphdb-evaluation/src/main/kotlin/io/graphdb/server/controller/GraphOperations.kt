package io.graphdb.server.controller

import io.graphdb.server.model.Person
import io.graphdb.server.model.PersonId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

interface GraphOperations {

    @GetMapping("/person")
    fun findPerson(
        @RequestParam("id", defaultValue = "1") id: PersonId,
    ): Person?

    @GetMapping("/friends")
    fun getPossibleFriends(
        @RequestParam("seed", defaultValue = "1") seed: PersonId,
        @RequestParam("degree", defaultValue = "1") degree: Int
    )
}
