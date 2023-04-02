package no.dnb.loan.realestateloanapi.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val roles = AuthorityUtils.authorityListToSet(authentication.authorities)
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/loan-api/adviser/page")
        } else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("/loan-api/customer/page")
        } else {
            response.sendRedirect("/loan-api")
        }
    }
}
