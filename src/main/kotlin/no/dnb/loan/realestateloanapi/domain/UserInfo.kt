package no.dnb.loan.realestateloanapi.domain

import java.time.Instant

data class UserInfo(
    val name: String,
    val email: String,
    val customerSSN: String,
    val password: String,
    val roles: String,
    val createdBy: String,
    val createdAt: Instant,
)

enum class Role {
    ADMIN,
    USER,
}
