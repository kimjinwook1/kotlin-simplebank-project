package com.kotlin.simplebankapp.domain.member.util

import com.kotlin.simplebankapp.domain.member.model.Member
import com.kotlin.simplebankapp.domain.member.model.vo.Birthday
import com.kotlin.simplebankapp.domain.member.model.vo.ProfileImage
import com.kotlin.simplebankapp.domain.member.model.vo.RoleType
import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import java.time.LocalDate

class GivenMember {
    companion object {
        fun toMember(): Member {
            return Member(
                "진욱",
                "jinwook",
                Birthday(LocalDate.of(1993, 6, 28)),
                UserEmail("jinwook628@gmail.com"),
                ProfileImage("profileImage"),
                "01031644306",
                RoleType.USER,
                false,
                "1234",
                1L
            )
        }
    }
}
