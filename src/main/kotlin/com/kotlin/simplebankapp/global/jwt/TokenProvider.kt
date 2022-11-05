package com.kotlin.simplebankapp.global.jwt

import com.kotlin.simplebankapp.global.jwt.dto.JwtTokenDto
import com.kotlin.simplebankapp.global.security.CustomUserDetailsService
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import kotlin.streams.toList


@Component
class TokenProvider(
    @param:Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.accessToken-validity-in-seconds}") private val accessTokenValidityInSeconds: Long,
    @Value("\${jwt.refreshToken-validity-in-seconds}") private val refreshTokenValidityInSeconds: Long,
    private val customUserDetailsService: CustomUserDetailsService
) : InitializingBean {

    private val logger = LoggerFactory.getLogger(TokenProvider::class.java)
    private var key: Key? = null

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(secret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createJwtTokenDto(memberId: Long?, authentication: Authentication): JwtTokenDto? {

        val accessTokenExpireTime = createAccessTokenExpireTime()
        val refreshTokenExpireTime = createRefreshTokenExpireTime()
        val accessToken = createAccessToken(memberId, authentication, accessTokenExpireTime)
        val refreshToken = createRefreshToken(refreshTokenExpireTime)

        return JwtTokenDto.fixture(accessToken, refreshToken)
    }

    private fun createAccessToken(memberId: Long?, authentication: Authentication, expirationTime: Date?): String {

        val authorities = authentication.authorities
            .map { GrantedAuthority::getAuthority }
            .toList().joinToString { "," }

        return Jwts.builder()
            .setExpiration(expirationTime) // 토큰 만료 시간
            .claim("memberId", memberId.toString()) // 회원 아이디
            .claim(AUTHORITIES_KEY, authorities) // 유저 role
            .setIssuedAt(Date()) // 토큰 발급 시간
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    private fun createRefreshToken(expirationTIme: Date?): String {

        return Jwts.builder()
            .setExpiration(expirationTIme) // 토큰 만료 시간
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    fun getAuthentication(token: String?): Authentication {

        val claims = Jwts.parserBuilder().setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        val authorities: Collection<GrantedAuthority> =
            Arrays.stream(claims[AUTHORITIES_KEY]
                .toString()
                .split(",".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray())
                .map { role: String? -> SimpleGrantedAuthority(role) }
                .toList()

        val memberId = claims["memberId"]

        val userDetails = customUserDetailsService.loadUserByUsername(memberId = memberId.toString())

        return UsernamePasswordAuthenticationToken(userDetails, "", authorities)
    }

    private fun createAccessTokenExpireTime(): Date {
        return Date(System.currentTimeMillis() + accessTokenValidityInSeconds)
    }

    private fun createRefreshTokenExpireTime(): Date {
        return Date(System.currentTimeMillis() + refreshTokenValidityInSeconds)
    }

    fun validateToken(token: String?): Boolean {

        try {
            Jwts.parserBuilder().setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            logger.info("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            logger.info("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            logger.info("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            logger.info("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            logger.info("JWT 토큰이 잘못되었습니다.")
        }
        return false
    }

    companion object {
        private const val AUTHORITIES_KEY = "auth"
    }
}
