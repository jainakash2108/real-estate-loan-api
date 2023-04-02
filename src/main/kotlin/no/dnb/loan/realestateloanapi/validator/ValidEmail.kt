package no.dnb.loan.realestateloanapi.validator

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ValidEmail(
    val message: String = "",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class EmailValidator : ConstraintValidator<ValidEmail, String> {
    override fun isValid(email: String, context: ConstraintValidatorContext): Boolean =
        when (isValid(email)) {
            true -> true
            false -> {
                context.disableDefaultConstraintViolation()
                context.buildConstraintViolationWithTemplate("Email is not valid")
                    .addConstraintViolation()
                false
            }
        }

    private fun isValid(email: String): Boolean {
        val emailRegex = Regex("""[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}""")
        return emailRegex.matches(email)
    }
}
