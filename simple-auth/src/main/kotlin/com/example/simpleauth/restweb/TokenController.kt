package com.example.simpleauth.restweb

import com.example.simpleauth.model.dto.AuthRequest
import com.example.simpleauth.model.dto.AuthResponse
import com.example.simpleauth.model.dto.JwtRequest
import com.example.simpleauth.model.dto.JwtResponse
import com.example.simpleauth.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth-simple/token")
class TokenController(
    val userService: UserService,
) {

    @PostMapping("/generate")
    fun generateToken(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(
            AuthResponse(userService.getTokenJWT(authRequest))
        )
    }

    @PostMapping("/parse")
    fun generateToken(@RequestBody jwtRequest: JwtRequest): ResponseEntity<JwtResponse> {
        return ResponseEntity.ok(
            JwtResponse(userService.getPayloadJWT(jwtRequest))
        )
    }
}