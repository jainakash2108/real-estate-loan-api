package no.dnb.loan.realestateloanapi.dao.api

import no.dnb.loan.realestateloanapi.domain.Loan

interface LoanDao {

    fun insert(loan: Loan): Loan

    fun selectAll(): List<Loan>

    fun deleteAll(): Int

    fun selectById(id: Long): Loan

    fun selectByCustomerSSN(customerSSN: String): List<Loan>
}
