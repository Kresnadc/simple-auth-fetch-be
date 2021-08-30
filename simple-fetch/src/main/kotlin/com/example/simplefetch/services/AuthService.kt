package com.example.simplefetch.services

import com.example.simplefetch.model.dto.JwtRequest
import io.jsonwebtoken.Claims

interface AuthService {
    fun getPayloadJWT(jwtRequest: JwtRequest): Claims
}