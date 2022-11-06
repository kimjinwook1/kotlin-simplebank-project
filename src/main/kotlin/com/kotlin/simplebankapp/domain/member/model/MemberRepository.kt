package com.kotlin.simplebankapp.domain.member.model

import com.kotlin.simplebankapp.domain.member.model.repository.MemberCustomRepository
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>, MemberCustomRepository {
}
