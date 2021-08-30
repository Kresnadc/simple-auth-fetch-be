package com.example.simplefetch.restweb

import com.example.simplefetch.model.dto.JwtRequest
import com.example.simplefetch.model.dto.JwtResponse
import com.example.simplefetch.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fetch-simple/token")
class TokenController(
    val authService: AuthService,
) {

    @PostMapping("/parse")
    fun generateToken(@RequestBody jwtRequest: JwtRequest): ResponseEntity<JwtResponse> {
        return ResponseEntity.ok(
            JwtResponse(authService.getPayloadJWT(jwtRequest))
        )
    }
}