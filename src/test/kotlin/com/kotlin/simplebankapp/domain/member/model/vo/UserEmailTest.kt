package com.kotlin.simplebankapp.domain.member.model.vo

import com.kotlin.simplebankapp.domain.member.util.GivenMember
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class UserEmailTest {

    @Test
    @DisplayName("email Id를 조회한다.")
    fun getEmailId() {
        //given
        val email = GivenMember.toMember().email
        val id = "jinwook628"

        //when
        val emailId = email.getId()

        //then
        assertThat(emailId).isEqualTo(id)
    }

    @Test
    @DisplayName("email Host를 조회한다.")
    fun getEmailHost() {
        //given
        val email = GivenMember.toMember().email
        val host = "gmail.com"

        //when
        val emailHost = email.getHost()

        //then
        assertThat(host).isEqualTo(emailHost)
    }


    @Test
    @DisplayName("UserEmail Equals 테스트")
    fun equalsTest() {
        //given
        val userEmail = UserEmail("jinwook628@gmail.com")
        //when
        val givenEmail = GivenMember.toMember().email

        //then
        assertThat(userEmail.value).isEqualTo(givenEmail.value)
        assertThat(userEmail).isEqualTo(givenEmail)
    }

}
