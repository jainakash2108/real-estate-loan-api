package no.dnb.loan.realestateloanapi.controller

import no.dnb.loan.realestateloanapi.domain.LoggedInUser
import no.dnb.loan.realestateloanapi.domain.Role
import no.dnb.loan.realestateloanapi.domain.UserRequest
import no.dnb.loan.realestateloanapi.service.UserService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController(private val userService: UserService) {

    @GetMapping("/user/info", produces = [APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    fun getLoggedInUser(): ResponseEntity<LoggedInUser> {
        val username = SecurityContextHolder.getContext().authentication.name
        val userInfo = userService.findLoggedInUser(username)
        val loggedInUser = LoggedInUser(
            name = userInfo.name,
            email = userInfo.email,
            customerId = userInfo.customerSSN.maskCustomerSSN(),
            role = Role.valueOf(userInfo.roles), // Considering that user is having only one role at max without comma
        )
        return ok().body(loggedInUser)
    }

    @PostMapping("/user/register")
    fun registerUser(
        @RequestBody @Validated
        userRequest: UserRequest,
    ): ResponseEntity<Any> {
        userService.createUser(userRequest, Role.USER, userRequest.customerSSN)
        return ok().build()
    }

    @PostMapping("/admin/register")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    fun registerAdmin(
        @RequestBody @Validated
        userRequest: UserRequest,
    ): ResponseEntity<Any> {
        val customerSSN = SecurityContextHolder.getContext().authentication.name
        userService.createUser(userRequest, Role.ADMIN, customerSSN)
        return ok().build()
    }
}

fun String.maskCustomerSSN(): String = "${this.substring(0, 6)}*****"
