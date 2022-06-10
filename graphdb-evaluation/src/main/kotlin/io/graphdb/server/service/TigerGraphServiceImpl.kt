package io.graphdb.server.service

import io.graphdb.server.model.Person
import io.graphdb.server.model.PersonId
import io.graphdb.server.repository.GraphRepository
import io.graphdb.server.repository.Vertex
import org.springframework.stereotype.Service

@Service
class TigerGraphServiceImpl(
    private val tigerGraphRepository: GraphRepository
) : GraphService {

    override fun insertPerson(person: Person) {
        tigerGraphRepository.insertVertex(Vertex.PERSON, person)
    }

    override fun findPersonById(id: PersonId): Person? {
        return tigerGraphRepository.findVertexById(Vertex.PERSON, id) as? Person
    }

    override fun deletePerson(id: PersonId) {
        tigerGraphRepository.deleteVertexById(Vertex.PERSON, id)
    }

    override fun findPossibleFriends(degree: Int, seed: PersonId) {
        tigerGraphRepository.friendsTraversal(degree, seed)
    }

}
