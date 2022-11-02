package com.kotlin.simplebankapp.domain.member.model.repository

import com.kotlin.simplebankapp.global.security.CustomUserDetails

interface MemberCustomRepository {

    fun getDetailById(memberId: Long): CustomUserDetails?

}
