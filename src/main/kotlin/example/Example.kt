package example

import org.neo4j.graphdb.Node
import org.neo4j.graphdb.RelationshipType
import org.neo4j.logging.Log
import org.neo4j.procedure.*

class Example {
    @Context
    @JvmField
    var log: Log? = null

    @Procedure(value = "example.connect", mode = Mode.WRITE)
    @Description("Connect 2 nodes with a given relationship type")
    fun connect(@Name("from") from: Node,
                @Name("type") type: String,
                @Name("to") to: Node) {
        from.createRelationshipTo(to, RelationshipType.withName(type))
        log!!.info("Created a new relationship using connect.")
    }

    @UserFunction(value = "example.concat")
    @Description("example.concat(['s1','s2',...], delimiter) - join the given strings with the given delimiter.")
    fun concat(
            @Name("strings") strings: List<String>?,
            @Name("delimiter") delimiter: String?
    ): String? {
        if (strings == null || delimiter == null) {
            return null
        }
        var result = ""
        for (string in strings) {
            result += string + delimiter
        }
        return result
    }
}