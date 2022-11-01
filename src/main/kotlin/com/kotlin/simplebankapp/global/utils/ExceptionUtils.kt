package com.kotlin.simplebankapp.global

import com.kotlin.simplebankapp.global.error.MemberNotFoundException
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun memberNotFound(memberId:Long): Nothing{
    throw MemberNotFoundException(memberId)
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id) ?: memberNotFound(id.toString().toLong())
}
