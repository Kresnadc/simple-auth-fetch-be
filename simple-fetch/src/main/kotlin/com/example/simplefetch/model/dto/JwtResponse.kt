package com.example.simplefetch.model.dto

import io.jsonwebtoken.Claims

data class JwtResponse(
    val claims: Claims,
)