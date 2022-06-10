package io.graphdb.server.controller

import io.graphdb.server.model.Person
import io.graphdb.server.model.PersonId
import io.graphdb.server.service.TigerGraphServiceImpl
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tigergraph")
class TigerGraphController(
    private val graphService: TigerGraphServiceImpl
) : GraphOperations {

    override fun findPerson(id: PersonId): Person? {
        return graphService.findPersonById(id)
    }

    override fun getPossibleFriends(
        @RequestParam(defaultValue = "1", value = "seed") seed: PersonId,
        @RequestParam(defaultValue = "1", value = "degree") degree: Int
    ) {
        graphService.findPossibleFriends(degree, seed)
    }

}
