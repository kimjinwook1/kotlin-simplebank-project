package com.kotlin.simplebankapp.global.error

enum class ErrorCode(val status: Int, val code: String, val message: String) {
    // Common
    METHOD_NOT_ALLOWED(405, "C001", "잘못된 요청입니다."),
    HANDLE_ACCESS_DENIED(400, "c002", "잘못된 접근입니다."),
    INTERNAL_SERVER_ERROR(500, "C005", "서버 내부 오류 입니다."),

    // Member
    MEMBER_NOT_FOUND(400, "M001", "멤버를 찾을 수 없습니다."),

    //Bithday
    EXCEED_BIRTHDAY(400, "B001", "생일이 현재 날짜를 초과하였습니다.")
}
