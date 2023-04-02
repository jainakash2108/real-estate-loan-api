package no.dnb.loan.realestateloanapi.exception

class LoanApplicationNotFound(message: String) : RuntimeException(message)

class IllegalLoanStatusException(message: String) : RuntimeException(message)

class UserAlreadyExists(message: String) : RuntimeException(message)
