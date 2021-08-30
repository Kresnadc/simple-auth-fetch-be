package com.example.simplefetch.restweb

import com.example.simplefetch.model.dto.FisheryResponse
import com.example.simplefetch.model.dto.JwtRequest
import com.example.simplefetch.services.AuthService
import com.example.simplefetch.services.FisheryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fetch-simple/fishery")
class FisheryController(
    val fisheryService: FisheryService,
    val authService: AuthService,
) {

    @GetMapping("/list")
    fun get(@RequestHeader("Authorization") token: String): ResponseEntity<List<FisheryResponse>> {
        authService.getPayloadJWT(JwtRequest(token))
        return ResponseEntity.ok(fisheryService.fetchData())
    }

    @GetMapping("/fetch")
    fun fetchFromOutbound(@RequestHeader("Authorization") token: String): ResponseEntity<String> {
        authService.getPayloadJWT(JwtRequest(token))
        return ResponseEntity.ok(fisheryService.fetchOutboundData())
    }
}