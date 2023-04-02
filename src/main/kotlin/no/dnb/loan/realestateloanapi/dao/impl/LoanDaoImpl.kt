package no.dnb.loan.realestateloanapi.dao.impl

import no.dnb.loan.realestateloanapi.controller.maskCustomerSSN
import no.dnb.loan.realestateloanapi.dao.api.LoanDao
import no.dnb.loan.realestateloanapi.dao.api.LoanStatusDao
import no.dnb.loan.realestateloanapi.dao.table.LoanTable
import no.dnb.loan.realestateloanapi.domain.Loan
import no.dnb.loan.realestateloanapi.domain.LoanStatus
import no.dnb.loan.realestateloanapi.exception.LoanApplicationNotFound
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class LoanDaoImpl(private val loanStatusDao: LoanStatusDao) : LoanDao {

    override fun insert(loan: Loan): Loan = transaction {
        val resultRow = LoanTable.insert {
            it[customerSSN] = loan.customerSSN
            it[loanAmount] = loan.loanAmount
            it[equityAmount] = loan.equityAmount
            it[salaryAmount] = loan.salaryAmount
        }.resultedValues
            ?.firstOrNull()
            ?: throw RuntimeException("Expected row to be present for customer ${loan.customerSSN.maskCustomerSSN()}")
        val id = resultRow[LoanTable.id]
        resultRow.toLoan(listOf(loanStatusDao.insert(loanId = id.value, loanStatus = loan.status.first())))
    }

    override fun selectAll(): List<Loan> = transaction {
        LoanTable.selectAll().map {
            it.toLoan(status = loanStatusDao.selectByLoanId(it[LoanTable.id].value))
        }
    }

    override fun deleteAll() = transaction { LoanTable.deleteAll() }

    override fun selectById(id: Long): Loan = transaction {
        LoanTable.select { LoanTable.id eq id }
            .map { it.toLoan(loanStatusDao.selectByLoanId(id)) }
            .firstOrNull() ?: throw LoanApplicationNotFound("Loan application $id does not exist")
    }

    override fun selectByCustomerSSN(customerSSN: String): List<Loan> = transaction {
        LoanTable.select { LoanTable.customerSSN eq customerSSN }
            .map { it.toLoan(loanStatusDao.selectByLoanId(it[LoanTable.id].value)) }
    }
}

private fun ResultRow.toLoan(status: List<LoanStatus>): Loan =
    Loan(
        id = this[LoanTable.id].value,
        customerSSN = this[LoanTable.customerSSN],
        loanAmount = this[LoanTable.loanAmount],
        equityAmount = this[LoanTable.equityAmount],
        salaryAmount = this[LoanTable.salaryAmount],
        status = status,
    )
