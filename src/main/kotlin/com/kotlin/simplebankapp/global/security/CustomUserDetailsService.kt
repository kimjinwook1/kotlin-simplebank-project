package com.kotlin.simplebankapp.global.security

import com.kotlin.simplebankapp.domain.member.model.MemberRepository
import com.kotlin.simplebankapp.global.common.RedisPolicy
import com.kotlin.simplebankapp.global.utils.memberNotFound
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {

    @Cacheable(value = [RedisPolicy.AUTH_KEY], key = "#memberId")
    override fun loadUserByUsername(memberId: String) =
        memberRepository.getDetailById(memberId.toLong()) ?: memberNotFound()
}
