import com.newfarm.builder.SqlSelectBuilder
import com.newfarm.builder.SqlSelectBuilder.build
import com.newfarm.builder.SqlSelectBuilder.query
import com.newfarm.builder.SqlSelectBuilder.selectAll
import com.newfarm.builder.SqlSelectBuilder.table
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs


class SqlOrmTest {

    private fun checkSQL(expected: String, sql: SqlSelectBuilder) {
        assertEquals(expected, sql.build())
    }

    @Test
    fun `simple select all from table`() {
        val expected = "SELECT * FROM table"

        val real = query { builder ->
            builder.selectAll()
            builder.table("table")
        }

        checkSQL(expected, real)
    }

    @Test
    fun `select params from table`() {
        val expected = "SELECT (param1, param2) FROM table"

        val real = query { builder ->
            builder.run {
                select(listOf("param1", "param2"))
                table("table")
            }
        }

        checkSQL(expected, real)
    }

    @Test
    fun `check runtime exception is thrown when table is not set`() {
        val expected = "SELECT (param1, param2) FROM table"

        try {
            query { builder ->
                builder.run {
                    select(listOf("param1", "param2"))
                }
            }
        } catch (exception: Exception) {
            assertIs<RuntimeException>(exception)
            assertEquals(exception.message, "table is not set")
        }
    }

    @Test
    fun `select with where with multiple conditions`() {
        val expected = "SELECT (param1, param2) FROM table WHERE id > 5 AND id < 25"

        val real = query { builder ->
            builder.run {
                select(listOf("param1", "param2"))
                table("table")
                where(listOf("id > 5", "id < 25"))
            }
        }

        checkSQL(expected, real)
    }

    @Test
    fun `select with order by`() {
        val expected = "SELECT (param1, param2) FROM table WHERE id > 5 AND id < 25 ORDER BY name"

        val real = query { builder ->
            builder.run {
                select(listOf("param1", "param2"))
                table("table")
                where(listOf("id > 5", "id < 25"))
                orderBy("name")
            }
        }

        checkSQL(expected, real)
    }
}