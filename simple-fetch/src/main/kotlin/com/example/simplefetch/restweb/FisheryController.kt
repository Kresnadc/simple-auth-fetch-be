package com.example.simplefetch.restweb

import com.example.simplefetch.model.dto.FisheryResponse
import com.example.simplefetch.services.AuthService
import com.example.simplefetch.services.FisheryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fetch-simple/fishery")
class FisheryController(
    val fisheryService: FisheryService,
) {

    @GetMapping("/list")
    fun get(): ResponseEntity<List<FisheryResponse>> {
        return ResponseEntity.ok(fisheryService.fetchData())
    }

    @GetMapping("/fetch")
    fun fetchFromOutbound(): ResponseEntity<String> {
        return ResponseEntity.ok(fisheryService.fetchOutboundData())
    }
}