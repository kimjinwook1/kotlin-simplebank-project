package com.kotlin.simplebankapp.global.jwt.dto

class JwtTokenDto(
    val accessToken: String,
    val refreshToken: String,
) {

    companion object {
        fun fixture(
            accessToken: String,
            refreshToken: String
        ): JwtTokenDto {
            return JwtTokenDto(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
    }
}
