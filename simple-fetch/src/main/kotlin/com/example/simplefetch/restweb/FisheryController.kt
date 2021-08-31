package com.example.simplefetch.restweb

import com.example.simplefetch.model.dao.AggregateResults
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

    @GetMapping("/list/aggregate")
    fun getAggregate(@RequestHeader("Authorization") token: String): ResponseEntity<List<AggregateResults>> {
        authService.getPayloadJWT(JwtRequest(token))
        return ResponseEntity.ok(fisheryService.fetchDataAggregate())
    }

//    @GetMapping("/fetch")
//    fun fetchFromOutbound(@RequestHeader("Authorization") token: String): ResponseEntity<String> {
//        return ResponseEntity.ok(fisheryService.fetchOutboundData())
//    }
}