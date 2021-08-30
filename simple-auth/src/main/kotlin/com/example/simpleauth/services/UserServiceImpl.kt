package com.example.simpleauth.services

import com.example.simpleauth.configuration.UserProp
import com.example.simpleauth.model.dto.UserRegistRequest
import com.example.simpleauth.model.dto.UserRegistResponse
import com.example.simpleauth.model.exception.UserAlreadyExistsException
import com.example.simpleauth.model.dao.User
import com.example.simpleauth.model.dto.AuthRequest
import com.example.simpleauth.model.dto.JwtRequest
import com.example.simpleauth.model.exception.AuthenticationFailedException
import com.example.simpleauth.repository.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import mu.KLogger
import mu.KotlinLogging
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userProp: UserProp,
    private val logger: KLogger = KotlinLogging.logger {}
) : UserService {

    override fun register(registRequest: UserRegistRequest): UserRegistResponse {
        var user = userRepository.findOneByName(registRequest.name)

        if (user != null) {
            logger.error("User name: ${user.name} is exists!")
            throw UserAlreadyExistsException("User name: ${user.name} is exists!")
        }

        user = User(
            null,
            LocalDateTime.now(),
            LocalDateTime.now(),
            registRequest.name,
            registRequest.phone,
            registRequest.role,
            RandomStringUtils.randomAlphanumeric(4),
        )

        val save = userRepository.save(user)

        return UserRegistResponse(save.password)
    }

    override fun getTokenJWT(authRequest: AuthRequest): String {
        var user = userRepository.findOneByPhoneAndPassword(
            authRequest.phone,
            authRequest.password
        )

        // Phone and Password doesn't match or user not exist
        if (user == null) {
            logger.error("Wrong user phone or password!")
            throw AuthenticationFailedException("Wrong user phone or password!")
        }

        // Build claims payload
        val claims: HashMap<String, Any?> = HashMap<String, Any?>();
        claims.put("name", user.name);
        claims.put("phone", user.phone);
        claims.put("role", user.role);

        // Generate token JWT
        return Jwts.builder()
            .setIssuer("backend")
            .setSubject("user")
            .addClaims(claims)
            .setIssuedAt(Date.from(Instant.now()))
            .signWith(
                SignatureAlgorithm.HS256,
                userProp.secret
            )
            .compact()
    }

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