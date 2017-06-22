package example

import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.neo4j.driver.v1.Config
import org.neo4j.driver.v1.GraphDatabase
import org.neo4j.harness.junit.Neo4jRule

class ExampleTest {
    // This rule starts a Neo4j instance
    @Rule
    @JvmField
    var neo4j = Neo4jRule()

            // This is the function we want to test
            .withFunction(Example::class.java)
            .withProcedure(Example::class.java)

    @Test
    @Throws(Throwable::class)
    fun shouldConcatStringsCorrectly() {
        // In a try-block, to make sure we close the driver and session after the test
        GraphDatabase.driver(neo4j.boltURI(), Config.build()
                .withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig()).use({ driver ->
            driver.session().use({ session ->
                // Given

                // When
                val result = session.run("RETURN example.concat(['name','surname'], ';') as result")

                // Then
                Assert.assertThat(result.single().get("result").asString(), IsEqual.equalTo("name;surname;"))
            })
        })
    }

    @Test
    @Throws(Throwable::class)
    fun shouldConnectNodesCorrectly() {
        // In a try-block, to make sure we close the driver and session after the test
        GraphDatabase.driver(neo4j.boltURI(), Config.build()
                .withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig()).use({ driver ->
            driver.session().use({ session ->
                // Given
                session.run("CREATE (p:From)")
                session.run("CREATE (p:To)")

                // When
                session.run("MATCH (f:From), (t:To) WITH f, t CALL example.connect(f, 'KNOWS', t) RETURN *")

                // Then
                val result = session.run("MATCH p=(f:From)-[:KNOWS]->(t:To) return p")
                Assert.assertThat(result.single().size(), IsEqual.equalTo(1))
            })
        })
    }
}