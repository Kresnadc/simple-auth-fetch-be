package com.example.simpleauth.restweb.advice

import com.example.simpleauth.model.dto.BaseResponse
import com.example.simpleauth.model.exception.AuthenticationFailedException
import com.example.simpleauth.model.exception.LogicException
import com.example.simpleauth.model.exception.UserAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(LogicException::class)])
    fun handleLogicException(ex: LogicException, request: WebRequest): ResponseEntity<BaseResponse<String>> {
        val errorDetails = BaseResponse(
            Date(),
            "Logic Failure",
            ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(UserAlreadyExistsException::class)])
    fun handleUserAlreadyExists(ex: UserAlreadyExistsException, request: WebRequest): ResponseEntity<BaseResponse<String>> {
        val errorDetails = BaseResponse(
            Date(),
            "User Already Exists",
            ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = [(AuthenticationFailedException::class)])
    fun handleAuthFailed(ex: AuthenticationFailedException, request: WebRequest): ResponseEntity<BaseResponse<String>> {
        val errorDetails = BaseResponse(
            Date(),
            "Auth Failed",
            ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.CONFLICT)
    }
}