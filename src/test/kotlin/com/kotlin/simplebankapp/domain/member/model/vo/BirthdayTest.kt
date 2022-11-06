package com.kotlin.simplebankapp.domain.member.model.vo

import com.kotlin.simplebankapp.domain.member.util.GivenMember
import com.kotlin.simplebankapp.global.error.ErrorCode
import com.kotlin.simplebankapp.global.error.ExceedBirthday
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

internal class BirthdayTest {

    @Test
    @DisplayName("생일 검증이 정상 동작한다.")
    fun validateExceedNow() {
        // given
        val exceedDate = LocalDate.now().plusDays(1)

        // then
        assertThrows<ExceedBirthday> { Birthday.fixture(exceedDate) }
            .apply {
                assertThat(message).isEqualTo(ErrorCode.EXCEED_BIRTHDAY.message)
            }

    }

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
