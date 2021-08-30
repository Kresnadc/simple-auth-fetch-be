package com.example.simplefetch.restweb.advice

import com.example.simplefetch.model.dto.BaseResponse
import com.example.simplefetch.model.exception.AuthenticationFailedException
import com.example.simplefetch.model.exception.LogicException
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