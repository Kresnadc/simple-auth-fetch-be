package com.example.simpleauth.model.dto

data class UserRegistRequest(
    val name: String,
    val phone: String,
    val role: String,
)