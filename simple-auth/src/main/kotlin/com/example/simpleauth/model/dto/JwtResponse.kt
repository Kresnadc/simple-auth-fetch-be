package com.example.simpleauth.model.dto

import io.jsonwebtoken.Claims

data class JwtResponse(
    val claims: Claims,
)