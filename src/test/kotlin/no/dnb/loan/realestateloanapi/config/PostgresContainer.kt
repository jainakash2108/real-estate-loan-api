package no.dnb.loan.realestateloanapi.config

import org.jetbrains.exposed.sql.Database
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

private val container =
    PostgreSQLContainer("postgres:15-alpine")
        .withDatabaseName("loanapi")
        .withUsername("loanapi")
        .withPassword("loanapi")
        .withStartupTimeout(30.seconds.toJavaDuration())

internal class PostgresContainer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        if (!container.isRunning) {
            container.start()
            Database.connect(
                url = container.jdbcUrl,
                user = container.username,
                password = container.password,
                driver = container.driverClassName,
            )
        }
        TestPropertyValues
            .of("spring.datasource.url=${container.jdbcUrl}")
            .applyTo(applicationContext.environment)
    }
}
