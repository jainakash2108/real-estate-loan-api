package no.dnb.loan.realestateloanapi.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ErrorMessage {
        val errors = ex.bindingResult.fieldErrors.map { it.defaultMessage }.first()
        log.error(errors)
        return ErrorMessage(errors)
    }

    @ExceptionHandler(UsernameNotFoundException::class, LoanApplicationNotFound::class, UserAlreadyExists::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleRuntimeException(ex: RuntimeException): ErrorMessage {
        log.error(ex.message)
        return ErrorMessage(ex.message)
    }

    @ExceptionHandler(IllegalLoanStatusException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalLoanStatusException(ex: IllegalLoanStatusException): ErrorMessage {
        log.error(ex.message)
        return ErrorMessage(ex.message)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(ex: Exception): ErrorMessage {
        log.error(ex.message)
        return ErrorMessage(ex.message)
    }
}
