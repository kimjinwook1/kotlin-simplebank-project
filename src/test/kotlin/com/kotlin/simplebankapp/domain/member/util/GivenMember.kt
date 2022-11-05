package com.kotlin.simplebankapp.domain.member.util

import com.kotlin.simplebankapp.domain.member.model.Member
import com.kotlin.simplebankapp.domain.member.model.vo.Birthday
import com.kotlin.simplebankapp.domain.member.model.vo.ProfileImage
import com.kotlin.simplebankapp.domain.member.model.vo.RoleType
import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import java.time.LocalDate

object GivenMember {
    var name = "진욱"
    var nickname = "jinwook"
    var birthday = Birthday(LocalDate.of(1993, 6, 28))
    var userEmail = UserEmail("jinwook628@gmail.com")
    var profileImage = ProfileImage("profileImage")
    var phoneNumber = "01031644306"
    var password = "1234"
    var id = 1L

    fun toMember(): Member {
        return Member(
            name,
            nickname,
            birthday,
            userEmail,
            profileImage,
            phoneNumber,
            RoleType.USER,
            false,
            password,
            id
        )
    }
}
