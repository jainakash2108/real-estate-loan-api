package no.dnb.loan.realestateloanapi.dao.impl

import no.dnb.loan.realestateloanapi.controller.maskCustomerSSN
import no.dnb.loan.realestateloanapi.dao.api.UserInfoDao
import no.dnb.loan.realestateloanapi.dao.table.UserInfoTable
import no.dnb.loan.realestateloanapi.domain.UserInfo
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserInfoDaoImpl : UserInfoDao {

    override fun selectByCustomerSSN(customerSSN: String): UserInfo? = transaction {
        UserInfoTable.select { UserInfoTable.customerSSN eq customerSSN }.map { it.toUserInfo() }.firstOrNull()
    }

    override fun insert(userInfo: UserInfo): UserInfo = transaction {
        UserInfoTable.insert {
            it[name] = userInfo.name
            it[email] = userInfo.email
            it[customerSSN] = userInfo.customerSSN
            it[password] = userInfo.password
            it[roles] = userInfo.roles
            it[createdBy] = userInfo.createdBy
            it[createdAt] = userInfo.createdAt
        }.resultedValues?.firstOrNull()?.toUserInfo()
            ?: throw RuntimeException("Expected row to be present for user ${userInfo.customerSSN.maskCustomerSSN()}")
    }

    override fun deleteAll(): Int = transaction { UserInfoTable.deleteAll() }
}

private fun ResultRow.toUserInfo(): UserInfo =
    UserInfo(
        name = this[UserInfoTable.name],
        email = this[UserInfoTable.email],
        customerSSN = this[UserInfoTable.customerSSN],
        password = this[UserInfoTable.password],
        roles = this[UserInfoTable.roles],
        createdBy = this[UserInfoTable.createdBy],
        createdAt = this[UserInfoTable.createdAt],
    )
