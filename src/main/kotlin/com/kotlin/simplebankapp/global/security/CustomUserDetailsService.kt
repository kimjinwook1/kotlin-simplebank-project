package com.kotlin.simplebankapp.global.security

import com.kotlin.simplebankapp.domain.member.model.repository.MemberCustomRepository
import com.kotlin.simplebankapp.domain.member.model.vo.RoleType
import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import com.kotlin.simplebankapp.global.common.RedisPolicy
import com.kotlin.simplebankapp.global.memberNotFound
import com.querydsl.core.annotations.QueryProjection
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

class CustomUserDetails @QueryProjection constructor(
        var memberId: Long,
        var email: UserEmail,
        var roleType: RoleType,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singleton(SimpleGrantedAuthority("ROLE_${roleType.name}"))
    }

    override fun getPassword(): String {
        // TODO 비밀번호 안주는게 맞나?
        return ""
    }

    override fun getUsername(): String {
        return email.value
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

@Service
class CustomUserDetailsService(
        private val memberCustomRepository: MemberCustomRepository
) : UserDetailsService {

    @Cacheable(value = [RedisPolicy.AUTH_KEY], key = "#memberId")
    override fun loadUserByUsername(memberId: String): UserDetails =
            memberCustomRepository.getDetailById(memberId.toLong()) ?: memberNotFound(memberId = memberId.toLong())
}
