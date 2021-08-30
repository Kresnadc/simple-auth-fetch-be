package com.example.simpleauth.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class UserProp {

    @Value("\${user.secret}")
    lateinit var secret: String
}