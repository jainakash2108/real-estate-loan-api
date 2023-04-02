package no.dnb.loan.realestateloanapi.dao.api

import no.dnb.loan.realestateloanapi.domain.LoanStatus

interface LoanStatusDao {

    fun insert(loanId: Long, loanStatus: LoanStatus): LoanStatus

    fun selectByLoanId(loanId: Long): List<LoanStatus>
}
