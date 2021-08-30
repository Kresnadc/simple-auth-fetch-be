package com.example.simpleauth.model.dto

data class AuthRequest(
    val phone: String,
    val password: String,
)