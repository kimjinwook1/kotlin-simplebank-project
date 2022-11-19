package com.kotlin.simplebankapp.domain.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.kotlin.simplebankapp.domain.member.model.Member
import java.time.LocalDate
import javax.validation.constraints.NotBlank

data class MemberJoinRequest(

    @field:NotBlank(message = "필수 값입니다. - email")
    @JsonProperty("email")
    val email: String,

    @field:NotBlank(message = "필수 값입니다. - password")
    @JsonProperty("password")
    val password: String,

    @field:NotBlank(message = "필수 값입니다. - name")
    @JsonProperty("name")
    val name: String,

    @field:NotBlank(message = "필수 값입니다. - nickname")
    @JsonProperty("nickname")
    val nickname: String,

    @field:NotBlank(message = "필수 값입니다. - LocalDate")
    @JsonProperty("LocalDate")
    val birth: LocalDate,

    @field:NotBlank(message = "필수 값입니다. - profile")
    @JsonProperty("profile")
    val profile: String,

    @field:NotBlank(message = "필수 값입니다. - phoneNumber")
    @JsonProperty("phoneNumber")
    val phoneNumber: String,
) {

    fun toEntity(): Member {
        return Member.fixture(
            name,
            nickname,
            birth,
            email,
            profile,
            phoneNumber,
            password
        )
    }

}
