package com.kotlin.simplebankapp.global

import com.kotlin.simplebankapp.global.error.ExceedBirthday
import com.kotlin.simplebankapp.global.error.MemberNotFoundException
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun memberNotFound(): Nothing = throw MemberNotFoundException()

fun exceedBirthday(): Nothing = throw ExceedBirthday()

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id) ?: memberNotFound()
}
