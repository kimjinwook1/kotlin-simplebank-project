package com.kotlin.simplebankapp.global.error

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.nio.file.AccessDeniedException

@RestControllerAdvice
class GlobalException {

    private val logger = LoggerFactory.getLogger(GlobalException::class.java)

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException?): ResponseEntity<ErrorResponse> {
        logger.error("HttpRequestMethodNotSupportedException", e)
        val response: ErrorResponse = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED)
        return ResponseEntity<ErrorResponse>(response, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        logger.error("MethodArgumentNotValidException", e)
        val message = e.bindingResult.allErrors[0].defaultMessage
        val response: ErrorResponse = ErrorResponse.createBinding(message.toString(), 400, "V001")
        return ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AccessDeniedException::class)
    protected fun handleAccessDeniedException(e: AccessDeniedException?): ResponseEntity<ErrorResponse> {
        logger.error("AccessDeniedException", e)
        val response: ErrorResponse = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED)
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.status))
    }

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        logger.error("handleBusinessException", e)
        val errorCode: ErrorCode = e.errorCode
        val response: ErrorResponse = ErrorResponse.of(errorCode)
        return ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception?): ResponseEntity<ErrorResponse> {
        logger.error("handleEntityNotFoundException", e)
        val response: ErrorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR)
        return ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
