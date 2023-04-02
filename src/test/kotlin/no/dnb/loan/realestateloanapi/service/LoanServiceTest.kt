package no.dnb.loan.realestateloanapi.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import no.dnb.loan.realestateloanapi.dao.api.LoanDao
import no.dnb.loan.realestateloanapi.dao.api.LoanStatusDao
import no.dnb.loan.realestateloanapi.dao.api.UserInfoDao
import no.dnb.loan.realestateloanapi.testFixtures.customerSSN
import no.dnb.loan.realestateloanapi.testFixtures.loanRequest
import no.dnb.loan.realestateloanapi.testFixtures.userInfo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.security.core.userdetails.UsernameNotFoundException

class LoanServiceTest {
    val loanDao = mockk<LoanDao>(relaxed = true)
    val loanStatusDao = mockk<LoanStatusDao>(relaxed = true)
    val userInfoDao = mockk<UserInfoDao>(relaxed = true)
    val loanService: LoanService = LoanService(loanDao, loanStatusDao, userInfoDao)

    @Test
    fun should_submit_loan_application() {
        every { userInfoDao.selectByCustomerSSN(customerSSN) } returns userInfo
        loanService.submitApplication(customerSSN, loanRequest)
        verify { loanDao.insert(any()) }
    }

    @Test
    fun should_throw_exception_if_user_does_not_exist() {
        every { userInfoDao.selectByCustomerSSN(customerSSN) } throws UsernameNotFoundException("User not found")
        assertThrows<UsernameNotFoundException> { loanService.submitApplication(customerSSN, loanRequest) }
        verify(exactly = 0) { loanDao.insert(any()) }
    }
}
