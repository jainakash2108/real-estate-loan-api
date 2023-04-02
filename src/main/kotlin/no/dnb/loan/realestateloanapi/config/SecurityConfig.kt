package no.dnb.loan.realestateloanapi.config

import no.dnb.loan.realestateloanapi.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
) {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity.csrf().disable().authorizeHttpRequests()
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/user/register/**", "/**", "/").permitAll()
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/loan/**", "/api/v1/user/info/**", "/api/v1/admin/register/**").authenticated()
            .and()
            .formLogin()
            .successHandler(customAuthenticationSuccessHandler) // Configure the success handler
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
            .and()
            .build()

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userService)
        authenticationProvider.setPasswordEncoder(passwordEncoder)
        return authenticationProvider
    }
}
