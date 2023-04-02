package no.dnb.loan.realestateloanapi.service

import no.dnb.loan.realestateloanapi.controller.maskCustomerSSN
import no.dnb.loan.realestateloanapi.dao.api.UserInfoDao
import no.dnb.loan.realestateloanapi.domain.Role
import no.dnb.loan.realestateloanapi.domain.UserInfo
import no.dnb.loan.realestateloanapi.domain.UserRequest
import no.dnb.loan.realestateloanapi.exception.UserAlreadyExists
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant.now

@Service
class UserService(private val userInfoDao: UserInfoDao, private val passwordEncoder: PasswordEncoder) :
    UserDetailsService {

    override fun loadUserByUsername(customerSSN: String): UserDetails {
        val userInfo = userInfoDao.selectByCustomerSSN(customerSSN) ?: throw UsernameNotFoundException("User not found")
        return User(
            userInfo.customerSSN,
            userInfo.password,
            userInfo.roles.split(",")
                .map { "ROLE_$it" }
                .map { SimpleGrantedAuthority(it) },
        )
    }

    fun findLoggedInUser(customerSSN: String): UserInfo =
        userInfoDao.selectByCustomerSSN(customerSSN) ?: throw UsernameNotFoundException("User not found")

    fun createUser(userRequest: UserRequest, role: Role, createdBy: String) {
        val userInfo = UserInfo(
            name = userRequest.name,
            email = userRequest.email,
            customerSSN = userRequest.customerSSN,
            password = passwordEncoder.encode(userRequest.password),
            roles = role.name,
            createdBy = createdBy,
            createdAt = now(),
        )
        val user = userInfoDao.selectByCustomerSSN(userInfo.customerSSN)
        if (user != null) {
            throw UserAlreadyExists("User is already registered with customer ssn ${userInfo.customerSSN.maskCustomerSSN()}")
        }
        userInfoDao.insert(userInfo)
    }
}
