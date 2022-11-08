package com.kotlin.simplebankapp.domain.member.dto

import java.time.LocalDate

class MemberUpdateRequest(
    val nickname: String,
    val birthDay: LocalDate,
    val profileImage: String,
    val name: String,
)
