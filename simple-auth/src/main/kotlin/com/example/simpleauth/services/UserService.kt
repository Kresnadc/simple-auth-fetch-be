package com.example.simpleauth.services

import com.example.simpleauth.model.dto.UserRegistRequest
import com.example.simpleauth.model.dto.UserRegistResponse

interface UserService {
    fun register(registRequest: UserRegistRequest): UserRegistResponse
}