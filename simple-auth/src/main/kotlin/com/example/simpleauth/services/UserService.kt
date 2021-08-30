package com.example.simpleauth.services

import com.example.simpleauth.model.dto.AuthRequest
import com.example.simpleauth.model.dto.JwtRequest
import com.example.simpleauth.model.dto.UserRegistRequest
import com.example.simpleauth.model.dto.UserRegistResponse
import io.jsonwebtoken.Claims

interface UserService {
    fun register(registRequest: UserRegistRequest): UserRegistResponse
    fun getTokenJWT(authRequest: AuthRequest): String
    fun getPayloadJWT(jwtRequest: JwtRequest): Claims
}