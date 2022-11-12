package com.kotlin.simplebankapp.global.utils

import com.kotlin.simplebankapp.global.error.BusinessException
import com.kotlin.simplebankapp.global.error.ErrorCode
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun memberNotFound(): Nothing = throw BusinessException(ErrorCode.MEMBER_NOT_FOUND)

fun fail(error: ErrorCode): Nothing = throw BusinessException(error)

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id) ?: memberNotFound()
}
