package com.kotlin.simplebankapp.domain.member.model.vo

import com.kotlin.simplebankapp.domain.member.util.GivenMember
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class BirthdayTest {

    @Test
    @DisplayName("BirthDay Equals 테스트")
    fun equalsTest() {
        //given
        val birthDay = Birthday(LocalDate.of(1993, 6, 28))

        //when
        val givenBirthday = GivenMember.toMember().birth

        //then
        assertThat(birthDay.value).isEqualTo(givenBirthday.value)
        assertThat(birthDay).isEqualTo(givenBirthday)

    }

}
