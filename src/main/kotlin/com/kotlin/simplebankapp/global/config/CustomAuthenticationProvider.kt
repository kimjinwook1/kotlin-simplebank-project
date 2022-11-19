package com.kotlin.simplebankapp.global.config

import com.kotlin.simplebankapp.global.security.CustomUserDetails
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class CustomAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val principal: CustomUserDetails = authentication?.principal as CustomUserDetails
        return UsernamePasswordAuthenticationToken(principal, principal.password, principal.authorities)
    }

    override fun supports(authentication: Class<*>?) = true
}
