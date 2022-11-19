package com.kotlin.simplebankapp.domain.member.dto

import com.kotlin.simplebankapp.domain.member.model.Member
import java.time.LocalDate

class MemberDetailResponse(
    val email: String,
    val name: String,
    val nickname: String,
    val birth: LocalDate,
    val profileImage: String,
    val phoneNumber: String
) {
    companion object {
        fun fixture(member: Member): MemberDetailResponse {
            return MemberDetailResponse(
                email = member.email.value,
                name = member.name,
                nickname = member.nickname,
                birth = member.birth.value,
                profileImage = member.profileImage.value,
                phoneNumber = member.phoneNumber
            )
        }
    }

}
