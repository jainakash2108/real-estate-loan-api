package no.dnb.loan.realestateloanapi.domain

import jakarta.validation.constraints.NotBlank
import no.dnb.loan.realestateloanapi.validator.ValidCustomerSSN
import no.dnb.loan.realestateloanapi.validator.ValidEmail
import no.dnb.loan.realestateloanapi.validator.ValidPassword

data class UserRequest(
    @field:NotBlank(message = "Name is required")
    val name: String,
    @field:ValidEmail
    val email: String,
    @field:ValidCustomerSSN
    val customerSSN: String,
    @field:ValidPassword
    val password: String,
)
