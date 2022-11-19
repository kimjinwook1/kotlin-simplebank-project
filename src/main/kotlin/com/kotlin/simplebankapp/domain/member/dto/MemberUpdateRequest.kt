package com.kotlin.simplebankapp.domain.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.NotBlank

class MemberUpdateRequest(

    @field:NotBlank(message = "필수 값입니다. - nickname")
    @JsonProperty("nickname")
    val nickname: String,

    @field:NotBlank(message = "필수 값입니다. - LocalDate")
    @JsonProperty("LocalDate")
    val birthDay: LocalDate,

    @field:NotBlank(message = "필수 값입니다. - profile")
    @JsonProperty("profile")
    val profileImage: String,

    @field:NotBlank(message = "필수 값입니다. - name")
    @JsonProperty("name")
    val name: String,
)
