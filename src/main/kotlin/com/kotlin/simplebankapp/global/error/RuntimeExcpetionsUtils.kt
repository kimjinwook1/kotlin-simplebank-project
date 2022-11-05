package com.kotlin.simplebankapp.global.error

class MemberNotFoundException : BusinessException(ErrorCode.MEMBER_NOT_FOUND)
open class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)
