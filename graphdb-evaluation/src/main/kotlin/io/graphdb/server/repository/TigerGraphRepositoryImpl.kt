package io.graphdb.server.repository

import com.zaxxer.hikari.HikariDataSource
import io.graphdb.server.model.Person
import io.graphdb.server.model.PersonId
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.sql.ResultSet

private data class TigerVertex(
    val name: String,
    val fields: List<String>
)

private fun Vertex.mapToTigerVertex(): TigerVertex {
    return when (this) {
        Vertex.PERSON -> TigerVertex(
            "person", mutableListOf("personId", "latitude", "longitude")
        )
        Vertex.VENUE -> throw Exception("Not Implemented")
    }
}

private fun ResultSet.toModel(vertex: Vertex): Any = when (vertex) {
    Vertex.PERSON -> {
        Person(
            this.getString("personId").toUInt(),
            this.getString("latitude"),
            this.getString("longitude")
        )
    }
    else -> throw Exception("Not Implemented")
}

@Repository
class TigerGraphRepositoryImpl(
    private val dataSource: HikariDataSource
) : GraphRepository {

    override fun insertVertex(vertex: Vertex, model: Any) {

        // To note: This implementation is an upsert instead of an insert operation

        val tigerVertex = vertex.mapToTigerVertex()
        val query = "INSERT INTO VERTEX ${tigerVertex.name}(${tigerVertex.fields.joinToString(",")}) VALUES (?, ?, ?)"

        dataSource.connection.use { connection ->
            connection.prepareStatement(query).use { preparedStatement ->
                preparedStatement.apply {
                    when (vertex) {
                        Vertex.PERSON -> {
                            model as Person
                            this.setObject(1, model.personId)
                            this.setString(2, model.latitude)
                            this.setString(3, model.longitude)
                        }
                        else ->
                            throw Exception("Vertex not implemented")
                    }
                }.let {
                    it.addBatch()
                    it.executeBatch()
                }.let { count ->
                    logger.info("Inserted ${count[0]} ${tigerVertex.name} vertex");
                }
            }
        }
    }

    override fun findVertexById(vertex: Vertex, id: UInt): Any? {

        val tigerVertex = vertex.mapToTigerVertex()
        val query = "get ${tigerVertex.name} (filter=?)"

        dataSource.connection.use { connection ->
            connection.prepareStatement(query).use { preparedStatement ->
                preparedStatement.apply {
                    this.setString(1, "${tigerVertex.fields.first()}=$id")
                }.executeQuery().use { resultSet ->
                    return resultSet.use {
                        generateSequence {
                            if (resultSet.next()) {
                                resultSet.toModel(vertex)
                            } else null
                        }.firstOrNull()
                    }
                }
            }
        }
    }

    override fun updateVertexById(vertex: Vertex, id: UInt, model: Any) {
        TODO("Not yet implemented")
    }

    override fun deleteVertexById(vertex: Vertex, id: UInt) {
        val tigerVertex = vertex.mapToTigerVertex()
        val query = "DELETE ${tigerVertex.name} (id=?)"

        dataSource.connection.use { connection ->
            connection.prepareStatement(query).use { preparedStatement ->
                preparedStatement.apply {
                    this.setString(1, id.toString())
                }.executeQuery().use { resultSet ->
                    return resultSet.use {
                        if (resultSet.next()) {
                            logger.info("${resultSet.getInt(1)} vertex deleted")
                        }
                    }
                }
            }
        }
    }

    override fun friendsTraversal(degree: Int, seed: PersonId) {

    }

    companion object {
        private val logger = LoggerFactory.getLogger(TigerGraphRepositoryImpl::class.java)
    }

}
