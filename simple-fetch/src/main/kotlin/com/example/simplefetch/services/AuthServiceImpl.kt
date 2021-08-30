package com.example.simplefetch.services

import com.example.simplefetch.configuration.UserProp
import com.example.simplefetch.model.dto.JwtRequest
import com.example.simplefetch.model.exception.AuthenticationFailedException
import com.example.simplefetch.repository.FisheryRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import mu.KLogger
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val fisheryRepository: FisheryRepository,
    private val userProp: UserProp,
    private val logger: KLogger = KotlinLogging.logger {}
) : AuthService {

    override fun getPayloadJWT(jwtRequest: JwtRequest): Claims {
        try {
            return Jwts.parser()
                .setSigningKey(userProp.secret) // parse with secret key/public key
                .parseClaimsJws(jwtRequest.token) // input token JWT
                .body // Get claims
        } catch (e: SignatureException) {
            throw AuthenticationFailedException(e.message)
        }
    }
}