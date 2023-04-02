package no.dnb.loan.realestateloanapi.dao.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

internal object LoanTable : IdTable<Long>("loan") {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()
    val customerSSN = text("customer_ssn").index()
    val loanAmount = decimal("loan_amount", 22, 2)
    val equityAmount = decimal("equity_amount", 22, 2)
    val salaryAmount = decimal("salary_amount", 22, 2)
}

internal object LoanStatusTable : IdTable<Long>("loan_status") {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()
    val loanId = long("loan_id").references(LoanTable.id, onDelete = ReferenceOption.CASCADE).index()
    val status = text("status")
    val description = text("description")
    val statusBy = text("status_by")
    val createdAt = timestamp("created_at").default(Instant.now())
    override val primaryKey = PrimaryKey(id, name = "pk_loan_status")
}
