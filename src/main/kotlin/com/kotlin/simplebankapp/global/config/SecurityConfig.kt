package com.kotlin.simplebankapp.global.config

import com.kotlin.simplebankapp.global.jwt.*
import com.kotlin.simplebankapp.global.security.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.CorsFilter

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val customUserDetailsService: CustomUserDetailsService,
    private val corsFilter: CorsFilter,
) {

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.csrf().disable()

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            .and()
            .headers().frameOptions().disable()

            .and()
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()

            .and()
            .apply(JwtSecurityConfig(tokenProvider))

            .and()
            .userDetailsService(customUserDetailsService)
            .apply(JwtSecurityConfig(tokenProvider))

        return httpSecurity.build()

    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().antMatchers(
                "/h2-console/**",
                "/favicon.ico",
                "/error"
            )
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? = BCryptPasswordEncoder()

    @Bean
    fun customAuthenticationProvider() = CustomAuthenticationProvider()
}
