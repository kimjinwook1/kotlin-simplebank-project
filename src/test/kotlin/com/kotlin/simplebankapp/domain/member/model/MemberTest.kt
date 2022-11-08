package com.kotlin.simplebankapp.domain.member.model

import com.kotlin.simplebankapp.domain.member.model.vo.Birthday
import com.kotlin.simplebankapp.domain.member.model.vo.ProfileImage
import com.kotlin.simplebankapp.domain.member.model.vo.RoleType
import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import com.kotlin.simplebankapp.domain.member.util.GivenMember
import com.kotlin.simplebankapp.domain.member.util.TestPasswordEncoder
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate


internal class MemberTest {

    private val passwordEncoder: TestPasswordEncoder = TestPasswordEncoder.init()

    @Test
    @DisplayName("멤버 업데이트가 정상 동작한다.")
    fun update() {
        val member = GivenMember.toMember()

        // given
        val updateName = "updateName"
        val updateNickname = "updateNickname"
        val updateProfile = ProfileImage("updateProfile")
        val updateBirth = Birthday.fixture(LocalDate.of(2000, 6, 28))

        // when
        member.update(updateName, updateNickname, updateProfile.value, updateBirth.value)

        // then
        assertThat(member.name).isEqualTo(updateName)
        assertThat(member.nickname).isEqualTo(updateNickname)
        assertThat(member.profileImage).isEqualTo(updateProfile)
        assertThat(member.birth).isEqualTo(updateBirth)
    }

    @Test
    @DisplayName("멤버 삭제가 정상 동작한다.")
    fun delete() {
        // given
        val member = GivenMember.toMember()

        // when
        member.delete()

        // then
        assertThat(member.isDeleted).isTrue
    }

    @Test
    @DisplayName("비밀번호 암호화가 정상 동작한다.")
    fun encodePassword() {
        // given
        val member = GivenMember.toMember()
        val password = member.password

        // when
        val encodedMember = member.encodePassword(passwordEncoder)

        // then
        assertThat((encodedMember.password)).isNotEqualTo(password)
    }

    @Test
    @DisplayName("입력한 비밀번호와 암호화된 비밀번호의 비교가 정상 동작한다.")
    fun matchPassword() {
        // given
        val member = GivenMember.toMember()
        val rawPassword = member.password

        val encodeMember = member.encodePassword(passwordEncoder)

        // when
        val matchPasswordResult = encodeMember.matchPassword(rawPassword, passwordEncoder)

        // then
        assertThat(matchPasswordResult).isTrue
    }

    @Test
    @DisplayName("비밀번호 변경이 정상 동작한다.")
    fun changePassword() {
        // given
        val member = GivenMember.toMember()
        val rawPassword = member.password

        // when
        val changePassword = "0000"
        member.changePassword(changePassword, passwordEncoder)

        // then
        assertThat(rawPassword).isNotEqualTo(member.password)
    }

    @DisplayName("멤버의 나이 조회가 정상 동작한다.")
    fun getAge() {
        // given
        val member = GivenMember.toMember()
        val birthDay = member.birth.value

        // when
        val age = member.getAge()

        // then
        assertThat(LocalDate.now().year - birthDay.year).isEqualTo(age)
    }

    @Test
    @DisplayName("멤버의 RolType 검증")
    fun memberRoleType() {
        //given
        val memberRole = GivenMember.toMember().role

        //then
        assertThat(RoleType.USER).isEqualTo(memberRole)
    }


    @Test
    @DisplayName("Member Equals 테스트")
    fun equalsTest() {
        //given
        val member = Member(
            "김진욱",
            "jinwook",
            Birthday(LocalDate.of(1993, 6, 28)),
            UserEmail("jinwook628@gmail.com"),
            ProfileImage("profileImage"),
            "01031644306",
            RoleType.USER,
            "1234"
        )

        //when
        val givenMember = GivenMember.toMember()

        //then
        assertThat(givenMember.id).isEqualTo(member.id)
        assertThat(givenMember).isEqualTo(member)

    }

}
