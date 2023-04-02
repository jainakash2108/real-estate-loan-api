package no.dnb.loan.realestateloanapi.dao.impl

import no.dnb.loan.realestateloanapi.dao.api.LoanStatusDao
import no.dnb.loan.realestateloanapi.dao.table.LoanStatusTable
import no.dnb.loan.realestateloanapi.domain.LoanStatus
import no.dnb.loan.realestateloanapi.domain.Status
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.time.Instant.now

@Repository
class LoanStatusDaoImpl : LoanStatusDao {

    override fun insert(loanId: Long, loanStatus: LoanStatus): LoanStatus = transaction {
        LoanStatusTable.insert {
            it[LoanStatusTable.loanId] = loanId
            it[status] = loanStatus.status.name
            it[description] = loanStatus.description
            it[statusBy] = loanStatus.statusBy
            it[createdAt] = now()
        }.resultedValues?.firstOrNull()?.toLoanStatus()
            ?: throw RuntimeException("Expected row to be present for loan $loanId")
    }

    override fun selectByLoanId(loanId: Long): List<LoanStatus> = transaction {
        LoanStatusTable.select { LoanStatusTable.loanId eq loanId }
            .orderBy(LoanStatusTable.createdAt to SortOrder.DESC)
            .map { it.toLoanStatus() }
    }
}

private fun ResultRow.toLoanStatus(): LoanStatus =
    LoanStatus(
        id = this[LoanStatusTable.id].value,
        status = Status.valueOf(this[LoanStatusTable.status]),
        description = this[LoanStatusTable.description],
        statusBy = this[LoanStatusTable.statusBy],
        createdAt = this[LoanStatusTable.createdAt],
    )
