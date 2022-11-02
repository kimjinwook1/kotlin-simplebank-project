package com.kotlin.simplebankapp.domain.member.model

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>
