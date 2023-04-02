package no.dnb.loan.realestateloanapi.validator

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.reflect.KClass

@Constraint(validatedBy = [CustomerSSNValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ValidCustomerSSN(
    val message: String = "",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class CustomerSSNValidator : ConstraintValidator<ValidCustomerSSN, String> {
    override fun isValid(customerSSN: String, context: ConstraintValidatorContext): Boolean = when {
        customerSSN.isBlank() -> {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("CustomerSSN is required")
                .addConstraintViolation()
            false
        }

        customerSSN.trim().length != 11 || customerSSN.isNotValid() -> {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("CustomerSSN is not valid")
                .addConstraintViolation()
            false
        }

        customerSSN.isBelow18() -> {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("Customer should be older than 18 years")
                .addConstraintViolation()
            false
        }

        else -> {
            true
        }
    }
}

private fun String.isNotValid(): Boolean {
    try {
        val validBirthDate = findBirthDate(this)
        if (validBirthDate > LocalDate.now()) {
            return true
        }
    } catch (e: Exception) {
        return true
    }
    return false
}

private fun String.isBelow18(): Boolean {
    val validBirthDate = findBirthDate(this)
    return ChronoUnit.YEARS.between(validBirthDate, LocalDate.now()) < 18
}

private fun findBirthDate(customerSSN: String): LocalDate {
    val day = customerSSN.substring(0, 2)
    val month = customerSSN.substring(2, 4)
    val year = customerSSN.substring(4, 6).toInt()
    val period = customerSSN.substring(6, 9).toInt()
    val birthYear = if (period < 500) 1900 + year else 2000 + year
    val birthDate = day + month + birthYear
    return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("ddMMyyyy"))
}
