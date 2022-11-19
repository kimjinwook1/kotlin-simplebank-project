package com.kotlin.simplebankapp.domain.member.model

import com.kotlin.simplebankapp.domain.member.model.repository.MemberCustomRepository
import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>, MemberCustomRepository {
    fun existsByNickname(nickname: String): Boolean
    fun existsByEmail(userEmail: UserEmail): Boolean
}
