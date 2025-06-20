package com.newfarm.builder

object SqlSelectBuilder {

    private var select: String? = null
    private var table: String? = null
    private var where: String? = null
    private var orderBy: String? = null

    fun query(block: (SqlSelectBuilder) -> Unit) : SqlSelectBuilder {
        block.invoke(this)
        return this
    }

    fun SqlSelectBuilder.selectAll() {
        select = "SELECT * FROM "
    }

    fun SqlSelectBuilder.select(selectParams: List<String>) {
        select = "SELECT (${selectParams.joinToString(separator = ", ")}) FROM "
    }

    fun SqlSelectBuilder.table(tableName: String) {
        table = tableName
    }

    fun SqlSelectBuilder.where(whereCondition: String) {
        where = " WHERE $whereCondition"
    }

    fun SqlSelectBuilder.where(whereConditions: List<String>) {
        where = " WHERE ${whereConditions.joinToString(separator = " AND ")}"
    }

    fun SqlSelectBuilder.orderBy(columnName: String) {
        orderBy = " ORDER BY $columnName"
    }

    fun SqlSelectBuilder.build() : String {
        val stringBuilder = StringBuilder()
        select?.run(stringBuilder::append) ?: throw RuntimeException("select is not set")
        table?.run(stringBuilder::append) ?: throw RuntimeException("table is not set")
        where?.run(stringBuilder::append)
        orderBy?.run(stringBuilder::append)
        return stringBuilder.toString().also {
            clearFields()
        }
    }

    private fun clearFields() {
        select = null
        table = null
        where = null
        orderBy = null
    }
}