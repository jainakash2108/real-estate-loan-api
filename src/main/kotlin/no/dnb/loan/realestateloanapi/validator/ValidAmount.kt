package no.dnb.loan.realestateloanapi.validator

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.beans.factory.annotation.Value
import java.math.BigDecimal
import kotlin.reflect.KClass

@Constraint(validatedBy = [AmountValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ValidAmount(
    val message: String = "",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class AmountValidator(
    @Value("\${amount.min}") val minAmount: BigDecimal,
    @Value("\${amount.max}") val maxAmount: BigDecimal,
) : ConstraintValidator<ValidAmount, BigDecimal> {
    override fun isValid(amount: BigDecimal, context: ConstraintValidatorContext): Boolean = when {
        amount < minAmount -> {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("Amount must be greater than or equal to $minAmount")
                .addConstraintViolation()
            false
        }

        amount > maxAmount -> {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("Amount must be less than or equal to $maxAmount")
                .addConstraintViolation()
            false
        }

        else -> {
            true
        }
    }
}
