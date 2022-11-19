package com.kotlin.simplebankapp.domain.member.util

import com.kotlin.simplebankapp.domain.member.model.Member
import java.time.LocalDate

object GivenMember {
    var name = "진욱"
    var nickname = "jinwook"
    var birthday = LocalDate.of(1993, 6, 28)
    var userEmail = "jinwook628@gmail.com"
    var profileImage = "profileImage"
    var phoneNumber = "01031644306"
    var password = "1234"

    fun toMember(): Member {
        return Member.fixture(
            name,
            nickname,
            birthday,
            userEmail,
            profileImage,
            phoneNumber,
            password,
        )
    }
}
