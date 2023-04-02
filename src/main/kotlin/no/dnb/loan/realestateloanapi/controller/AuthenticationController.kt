package no.dnb.loan.realestateloanapi.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping
class AuthenticationController {

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        if (auth != null) {
            SecurityContextHolder.getContext().authentication = null
            request.getSession(false)?.invalidate()
        }
        return ModelAndView("redirect:/")
    }
}
