package com.example.simplefetch.services

import com.example.simplefetch.model.dto.FisheryResponse
import com.example.simplefetch.model.dto.JwtRequest
import io.jsonwebtoken.Claims

interface FisheryService {
    fun fetchData(): List<FisheryResponse>
    fun fetchOutboundData(): String
}