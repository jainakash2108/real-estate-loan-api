package no.dnb.loan.realestateloanapi.controller

import no.dnb.loan.realestateloanapi.service.LoanService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/loan")
class AdviserController(private val loanService: LoanService) {

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/view/applications", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun viewLoanApplications(): ResponseEntity<Any> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val userDetails = authentication.principal as UserDetails
        return ResponseEntity.ok().body(loanService.findLoanApplications(userDetails))
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/review/{applicationId}")
    fun reviewLoanApplication(@PathVariable applicationId: Long): ResponseEntity<Any> {
        val customerSSN = SecurityContextHolder.getContext().authentication.name
        loanService.reviewApplication(applicationId, customerSSN)
        return ResponseEntity.ok().build()
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/approve/{applicationId}")
    fun approveLoanApplication(@PathVariable applicationId: Long): ResponseEntity<Any> {
        val customerSSN = SecurityContextHolder.getContext().authentication.name
        loanService.approveApplication(applicationId, customerSSN)
        return ResponseEntity.ok().build()
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/reject/{applicationId}")
    fun rejectLoanApplication(@PathVariable applicationId: Long): ResponseEntity<Any> {
        val customerSSN = SecurityContextHolder.getContext().authentication.name
        loanService.rejectApplication(applicationId, customerSSN)
        return ResponseEntity.ok().build()
    }
}
