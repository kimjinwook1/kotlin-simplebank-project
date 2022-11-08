package com.kotlin.simplebankapp.global.utils

import com.kotlin.simplebankapp.global.error.ErrorCode
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun memberNotFound(): Nothing = throw RuntimeException(ErrorCode.MEMBER_NOT_FOUND.message)

fun fail(error: ErrorCode): Nothing = throw RuntimeException(error.message)

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id) ?: memberNotFound()
}
