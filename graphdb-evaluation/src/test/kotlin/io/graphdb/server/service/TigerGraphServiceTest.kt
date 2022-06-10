package io.graphdb.server.service

import io.graphdb.server.model.Person
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TigerGraphServiceTest(
    @Autowired private val tigerGraphService: TigerGraphServiceImpl
) {
    @Test
    fun `Test CRUD Operation on Person Vertex`() {

        val person = Person(
            personId = 123456u,
            latitude = "123",
            longitude = "234"
        )

        tigerGraphService.insertPerson(person)
        assertEquals(person, tigerGraphService.findPersonById(person.personId), "Mismatch in inserted value")

        tigerGraphService.deletePerson(person.personId)
        assertEquals(null, tigerGraphService.findPersonById(person.personId), "Mismatch in inserted value")

    }
}
