package com.kotlin.simplebankapp.global.security

import com.kotlin.simplebankapp.domain.member.model.vo.RoleType
import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import com.querydsl.core.annotations.QueryProjection
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class CustomUserDetails @QueryProjection constructor(
    var memberId: Long,
    var email: UserEmail,
    var roleType: RoleType,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singleton(SimpleGrantedAuthority("ROLE_${roleType.name}"))
    }

    override fun getPassword() = ""

    override fun getUsername() = email.value

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
