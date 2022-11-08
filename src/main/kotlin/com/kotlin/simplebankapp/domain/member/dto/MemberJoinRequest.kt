package com.kotlin.simplebankapp.domain.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.kotlin.simplebankapp.domain.member.model.Member
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class MemberJoinRequest(

    @NotNull(message = "필수 값입니다. - email")
    val email: String,

    @NotNull(message = "필수 값입니다. - password")
    val password: String,

    @NotNull(message = "필수 값입니다. - name")
    val name: String,

    @Valid
    @NotNull(message = "필수 값입니다. - nickname")
    val nickname: String,

    @Valid
    @NotNull(message = "필수 값입니다. - birth")
    val birth: LocalDate,

    @JsonProperty("profile")
    val profile: String,

    @NotNull(message = "필수 값입니다. - phoneNumber")
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
