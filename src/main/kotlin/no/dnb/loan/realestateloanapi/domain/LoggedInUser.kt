package no.dnb.loan.realestateloanapi.domain

data class LoggedInUser(
    val name: String,
    val email: String,
    val customerId: String,
    val role: Role,
)
