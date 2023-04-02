package no.dnb.loan.realestateloanapi.domain

import no.dnb.loan.realestateloanapi.validator.ValidAmount
import java.math.BigDecimal

data class LoanRequest(
    @field:ValidAmount
    val loanAmount: BigDecimal,
    @field:ValidAmount
    val equityAmount: BigDecimal,
    @field:ValidAmount
    val salaryAmount: BigDecimal,
)
