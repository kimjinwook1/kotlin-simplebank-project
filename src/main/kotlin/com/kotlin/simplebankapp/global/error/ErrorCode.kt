package com.kotlin.simplebankapp.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // Common
    METHOD_NOT_ALLOWED(405, "C001", "잘못된 요청입니다."),
    HANDLE_ACCESS_DENIED(400, "c002", "잘못된 접근입니다."),
    INTERNAL_SERVER_ERROR(500, "C005", "서버 내부 오류 입니다."),
}
