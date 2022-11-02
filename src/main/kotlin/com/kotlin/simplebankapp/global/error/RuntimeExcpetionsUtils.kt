package com.kotlin.simplebankapp.global.error

class MemberNotFoundException(memberId: Long) : RuntimeException("회원을 찾을 수 없습니다. $memberId")
class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)
