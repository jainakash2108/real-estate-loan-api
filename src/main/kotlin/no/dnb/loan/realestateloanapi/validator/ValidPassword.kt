package no.dnb.loan.realestateloanapi.validator

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [PasswordValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ValidPassword(
    val message: String = "",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class PasswordValidator : ConstraintValidator<ValidPassword, String> {
    override fun isValid(password: String, context: ConstraintValidatorContext): Boolean =
        when (isValid(password)) {
            true -> true
            false -> {
                context.disableDefaultConstraintViolation()
                context.buildConstraintViolationWithTemplate("Minimum length of 8 and maximum length of 12, At least one uppercase letter, one lowercase letter, one numeric value, and one special character")
                    .addConstraintViolation()
                false
            }
        }

    private fun isValid(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$")
        return passwordRegex.matches(password)
    }
}
