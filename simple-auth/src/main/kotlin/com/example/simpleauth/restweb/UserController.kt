package com.example.simpleauth.restweb

import com.example.simpleauth.model.dto.UserRegistRequest
import com.example.simpleauth.model.dto.UserRegistResponse
import com.example.simpleauth.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth-simple/user")
class UserController(
    val userService: UserService,
) {

    @PostMapping
    fun register(@RequestBody userRegist: UserRegistRequest): ResponseEntity<UserRegistResponse> {
        return ResponseEntity.ok(userService.register(userRegist))
    }
}