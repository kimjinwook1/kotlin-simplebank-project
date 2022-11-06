package com.kotlin.simplebankapp.domain.member.model.repository

import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import com.kotlin.simplebankapp.global.security.CustomUserDetails

interface MemberCustomRepository {

    fun getDetailById(memberId: Long): CustomUserDetails?
    fun findUserDetailsByEmail(email: UserEmail): CustomUserDetails?

}
