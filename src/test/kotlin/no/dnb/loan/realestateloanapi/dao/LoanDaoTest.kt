package no.dnb.loan.realestateloanapi.dao

import no.dnb.loan.realestateloanapi.config.PostgresContainer
import no.dnb.loan.realestateloanapi.dao.api.LoanDao
import no.dnb.loan.realestateloanapi.dao.api.LoanStatusDao
import no.dnb.loan.realestateloanapi.dao.api.UserInfoDao
import no.dnb.loan.realestateloanapi.dao.impl.LoanDaoImpl
import no.dnb.loan.realestateloanapi.dao.impl.LoanStatusDaoImpl
import no.dnb.loan.realestateloanapi.dao.impl.UserInfoDaoImpl
import no.dnb.loan.realestateloanapi.testFixtures.loan
import no.dnb.loan.realestateloanapi.testFixtures.userInfo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.test.context.ContextConfiguration

@JdbcTest
@ContextConfiguration(initializers = [PostgresContainer::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class LoanDaoTest {
    val loanStatusDao: LoanStatusDao = LoanStatusDaoImpl()
    val loanDao: LoanDao = LoanDaoImpl(loanStatusDao)
    val userInfoDao: UserInfoDao = UserInfoDaoImpl()

    @BeforeEach
    fun cleanUp() {
        loanDao.deleteAll()
        userInfoDao.deleteAll()
        userInfoDao.insert(userInfo)
    }

    @Test
    fun should_able_to_save_loan_request() {
        val insertedLoan = loanDao.insert(loan)
        Assertions.assertEquals(insertedLoan.customerSSN, loan.customerSSN)
    }

    @Test
    fun should_able_to_fetch_all_applications() {
        loanDao.insert(loan)
        loanDao.insert(loan)
        loanDao.insert(loan)
        loanDao.insert(loan)
        val loans = loanDao.selectAll()
        Assertions.assertEquals(4, loans.size)
    }
}
