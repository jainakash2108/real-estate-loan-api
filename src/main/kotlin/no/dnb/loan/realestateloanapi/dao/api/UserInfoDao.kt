package no.dnb.loan.realestateloanapi.dao.api

import no.dnb.loan.realestateloanapi.domain.UserInfo

interface UserInfoDao {

    fun selectByCustomerSSN(customerSSN: String): UserInfo?

    fun insert(userInfo: UserInfo): UserInfo

    fun deleteAll(): Int
}
