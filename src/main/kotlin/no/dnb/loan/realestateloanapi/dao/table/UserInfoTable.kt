package no.dnb.loan.realestateloanapi.dao.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

internal object UserInfoTable : Table("user_info") {
    val name = text("name")
    val email = text("email")
    val customerSSN = text("customer_ssn")
    val password = text("password")
    val roles = text("roles")
    val createdBy = text("created_by")
    val createdAt = timestamp("created_at")
    override val primaryKey = PrimaryKey(customerSSN, name = "pk_user_info")
}
