package no.dnb.loan.realestateloanapi.service

import no.dnb.loan.realestateloanapi.dao.api.LoanDao
import no.dnb.loan.realestateloanapi.dao.api.LoanStatusDao
import no.dnb.loan.realestateloanapi.dao.api.UserInfoDao
import no.dnb.loan.realestateloanapi.domain.Loan
import no.dnb.loan.realestateloanapi.domain.LoanRequest
import no.dnb.loan.realestateloanapi.domain.LoanStatus
import no.dnb.loan.realestateloanapi.domain.Status
import no.dnb.loan.realestateloanapi.exception.IllegalLoanStatusException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.Instant.now

@Service
class LoanService(
    private val loanDao: LoanDao,
    private val loanStatusDao: LoanStatusDao,
    private val userInfoDao: UserInfoDao,
) {

    fun submitApplication(customerSSN: String, loanRequest: LoanRequest) {
        val userInfo = userInfoDao.selectByCustomerSSN(customerSSN) ?: throw UsernameNotFoundException("User not found")
        val loan = Loan(
            customerSSN = userInfo.customerSSN,
            loanAmount = loanRequest.loanAmount,
            equityAmount = loanRequest.equityAmount,
            salaryAmount = loanRequest.salaryAmount,
            status = listOf(
                LoanStatus(
                    status = Status.SUBMITTED,
                    description = "Application has been submitted",
                    statusBy = userInfo.name,
                    createdAt = now(),
                ),
            ),
        )
        loanDao.insert(loan)
    }

    fun findLoanApplications(userDetails: UserDetails): List<Loan> =
        if (userDetails.authorities.any { it.authority.equals("ROLE_ADMIN") }) {
            loanDao.selectAll()
        } else {
            loanDao.selectByCustomerSSN(userDetails.username)
        }

    fun reviewApplication(applicationId: Long, customerSSN: String) {
        val userInfo = userInfoDao.selectByCustomerSSN(customerSSN) ?: throw UsernameNotFoundException("User not found")
        val loan: Loan = loanDao.selectById(applicationId)
        loanStatusDao.insert(
            loan.id ?: throw RuntimeException("Loan application $applicationId should be present"),
            LoanStatus(
                status = Status.IN_REVIEW,
                description = "Application is under review",
                statusBy = userInfo.name,
                createdAt = now(),
            ),
        )
    }

    fun approveApplication(applicationId: Long, customerSSN: String) {
        val userInfo = userInfoDao.selectByCustomerSSN(customerSSN) ?: throw UsernameNotFoundException("User not found")
        val loan: Loan = loanDao.selectById(applicationId)
        when (loan.status.first().status) {
            Status.IN_REVIEW -> loanStatusDao.insert(
                loan.id ?: throw RuntimeException("Loan application $applicationId should be present"),
                LoanStatus(
                    status = Status.APPROVED,
                    description = "Application has been approved",
                    statusBy = userInfo.name,
                    createdAt = now(),
                ),
            )

            else -> throw IllegalLoanStatusException("Loan application status is not in REVIEW")
        }
    }

    fun rejectApplication(applicationId: Long, customerSSN: String) {
        val userInfo = userInfoDao.selectByCustomerSSN(customerSSN) ?: throw UsernameNotFoundException("User not found")
        val loan: Loan = loanDao.selectById(applicationId)
        when (loan.status.first().status) {
            Status.IN_REVIEW -> loanStatusDao.insert(
                loan.id ?: throw RuntimeException("Loan application $applicationId should be present"),
                LoanStatus(
                    status = Status.REJECTED,
                    description = "Application has been rejected",
                    statusBy = userInfo.name,
                    createdAt = now(),
                ),
            )

            else -> throw IllegalLoanStatusException("Loan application status is not in REVIEW")
        }
    }

    fun withdrawApplication(applicationId: Long, customerSSN: String) {
        val userInfo = userInfoDao.selectByCustomerSSN(customerSSN) ?: throw UsernameNotFoundException("User not found")
        val loan: Loan = loanDao.selectById(applicationId)
        when (loan.status.first().status) {
            Status.APPROVED -> throw IllegalLoanStatusException("Loan application can not be withdrawn once approved")
            Status.REJECTED -> throw IllegalLoanStatusException("Loan application can not be withdrawn once rejected")
            else -> loanStatusDao.insert(
                loan.id ?: throw RuntimeException("Loan application $applicationId should be present"),
                LoanStatus(
                    status = Status.WITHDRAW,
                    description = "Application has been dropped",
                    statusBy = userInfo.name,
                    createdAt = now(),
                ),
            )
        }
    }
}
