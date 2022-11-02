package com.kotlin.simplebankapp.domain.member.model.vo

import com.kotlin.simplebankapp.domain.member.util.GivenMember
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ProfileImageTest {

    @Test
    @DisplayName("이미지 경로 검증이 정상 동작한다.")
    fun validateImagePath() {
        //given
        val member = GivenMember.toMember()
        val memberProfileImage = member.profileImage
        val profileImage = ProfileImage(memberProfileImage.value)

        //when
        val isValidateImagePath = memberProfileImage.validateImagePath(profileImage)

        //then
        assertThat(isValidateImagePath).isTrue
    }


    @Test
    @DisplayName("ProfileImage Equals 테스트")
    fun equalsTest() {
        //given
        val profileImage = ProfileImage("profileImage")
        //when
        val givenProfileImage = GivenMember.toMember().profileImage

        //then
        assertThat(profileImage.value).isEqualTo(givenProfileImage.value)
        assertThat(profileImage).isEqualTo(givenProfileImage)
    }

}
