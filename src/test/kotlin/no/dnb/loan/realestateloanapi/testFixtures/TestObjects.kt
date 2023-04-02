package no.dnb.loan.realestateloanapi.testFixtures

import no.dnb.loan.realestateloanapi.domain.Loan
import no.dnb.loan.realestateloanapi.domain.LoanRequest
import no.dnb.loan.realestateloanapi.domain.LoanStatus
import no.dnb.loan.realestateloanapi.domain.Status
import no.dnb.loan.realestateloanapi.domain.UserInfo
import no.dnb.loan.realestateloanapi.domain.UserRequest
import java.math.BigDecimal
import java.time.Instant

val customerSSN = "23047812367"

val userInfo: UserInfo = UserInfo(
    name = "Julian",
    email = "Julian@loanapi.no",
    customerSSN = "23047812367",
    password = "Test@1234",
    roles = "ADMIN",
    createdBy = "23047812367",
    createdAt = Instant.now(),
)

val submittedLoanStatus: LoanStatus = LoanStatus(
    status = Status.SUBMITTED,
    description = "",
    statusBy = "",
    createdAt = Instant.now(),
)

val inReviewLoanStatus: LoanStatus = LoanStatus(
    status = Status.IN_REVIEW,
    description = "",
    statusBy = "",
    createdAt = Instant.now(),
)

val loan: Loan = Loan(
    customerSSN = "23047812367",
    loanAmount = BigDecimal(100000.00),
    equityAmount = BigDecimal(100000.00),
    salaryAmount = BigDecimal(100000.00),
    status = listOf(inReviewLoanStatus, submittedLoanStatus),
)

val loanRequest: LoanRequest = LoanRequest(
    loanAmount = BigDecimal(100000.00),
    equityAmount = BigDecimal(100000.00),
    salaryAmount = BigDecimal(100000.00),
)

val userRequest: UserRequest = UserRequest(
    name = "Julian",
    email = "Julian@loanapi.no",
    customerSSN = "23047812367",
    password = "Test@1234",
)
