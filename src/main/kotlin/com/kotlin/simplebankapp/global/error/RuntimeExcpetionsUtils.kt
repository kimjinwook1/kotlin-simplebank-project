package com.kotlin.simplebankapp.global.error

open class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)
