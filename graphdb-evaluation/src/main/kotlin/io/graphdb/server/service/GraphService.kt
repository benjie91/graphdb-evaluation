package io.graphdb.server.service

import io.graphdb.server.model.Person
import io.graphdb.server.model.PersonId

interface GraphService {

    // CRUD Operations
    fun insertPerson(person: Person)
    fun findPersonById(id: PersonId): Person?
    fun deletePerson(id: PersonId)

    // Traversal Operations
    fun findPossibleFriends(degree: Int, seed: PersonId)

}
