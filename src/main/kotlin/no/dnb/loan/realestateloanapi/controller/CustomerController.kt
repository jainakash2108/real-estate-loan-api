package no.dnb.loan.realestateloanapi.controller

import no.dnb.loan.realestateloanapi.domain.LoanRequest
import no.dnb.loan.realestateloanapi.service.LoanService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/loan")
class CustomerController(private val loanService: LoanService) {

    @PostMapping("/apply", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAuthority('ROLE_USER')")
    fun submitLoanApplication(
        @RequestBody @Validated
        loanRequest: LoanRequest,
    ): ResponseEntity<Any> {
        val customerSSN = SecurityContextHolder.getContext().authentication.name
        loanService.submitApplication(customerSSN, loanRequest)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/withdraw/{applicationId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    fun withdrawLoanApplication(@PathVariable applicationId: Long): ResponseEntity<Any> {
        val customerSSN = SecurityContextHolder.getContext().authentication.name
        loanService.withdrawApplication(applicationId, customerSSN)
        return ResponseEntity.ok().build()
    }
}
