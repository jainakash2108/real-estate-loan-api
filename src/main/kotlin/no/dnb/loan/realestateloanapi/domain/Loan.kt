package no.dnb.loan.realestateloanapi.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.time.Instant

data class Loan(
    val id: Long? = null,
    @JsonIgnore
    val customerSSN: String,
    val loanAmount: BigDecimal,
    val equityAmount: BigDecimal,
    val salaryAmount: BigDecimal,
    val status: List<LoanStatus>,
)

data class LoanStatus(
    @JsonIgnore
    val id: Long? = null,
    val status: Status,
    val description: String,
    @JsonIgnore
    val statusBy: String,
    val createdAt: Instant,
)

enum class Status {
    SUBMITTED,
    IN_REVIEW,
    APPROVED,
    REJECTED,
    WITHDRAW,
}
