package io.graphdb.server.repository

import io.graphdb.server.model.PersonId

enum class Vertex { PERSON, VENUE }

interface GraphRepository {

    // Vertex Operations
    fun insertVertex(vertex: Vertex, model: Any)
    fun findVertexById(vertex: Vertex, id: UInt): Any?
    fun updateVertexById(vertex: Vertex, id: UInt, model: Any)
    fun deleteVertexById(vertex: Vertex, id: UInt)

    // Data Specific Operations
    fun friendsTraversal(degree: Int, seed: PersonId)

}
