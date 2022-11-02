package com.kotlin.simplebankapp.global.error

class ErrorResponse {
    private var message: String
    private var status: Int
    private var code: String

    private constructor(errorCode: ErrorCode) {
        message = errorCode.message
        status = errorCode.status
        code = errorCode.code
    }

    private constructor(message: String, status: Int, code: String) {
        this.message = message
        this.status = status
        this.code = code
    }

    companion object {
        fun createBinding(message: String, status: Int, code: String): ErrorResponse {
            return ErrorResponse(message, status, code)
        }

        fun of(errorCode: ErrorCode): ErrorResponse {
            return ErrorResponse(errorCode)
        }
    }
}
