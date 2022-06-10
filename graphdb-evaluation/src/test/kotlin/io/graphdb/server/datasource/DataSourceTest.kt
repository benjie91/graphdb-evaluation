package io.graphdb.server.datasource

import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest
import org.springframework.boot.test.context.SpringBootTest
import java.sql.ResultSetMetaData

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataSourceTest(
    @Autowired private val dataSource: HikariDataSource
) {
    @Test
    fun `Test TigerGraph Connection`() {
        dataSource.connection.createStatement().use { stmt ->
            val query = "builtins stat_vertex_number"
            stmt.executeQuery(query).use { rs ->
                do {
                    val metaData: ResultSetMetaData = rs.metaData
                    // Gets the name of the designated column (1-based indexing)
                    print(metaData.getColumnName(1))
                    for (i in 2..metaData.columnCount) {
                        print("\t" + metaData.getColumnName(i))
                    }
                    println("")
                    while (rs.next()) {
                        print(rs.getObject(1))
                        for (i in 2..metaData.columnCount) {
                            val obj: Any = rs.getObject(i)
                            println("\t" + obj.toString())
                        }
                    }
                } while (!rs.isLast)
            }
        }
    }
}
