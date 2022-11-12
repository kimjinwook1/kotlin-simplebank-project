package com.kotlin.simplebankapp.domain.member.service

import com.kotlin.simplebankapp.domain.member.dto.MemberJoinRequest
import com.kotlin.simplebankapp.domain.member.dto.MemberUpdateRequest
import com.kotlin.simplebankapp.domain.member.model.MemberRepository
import com.kotlin.simplebankapp.domain.member.model.vo.Birthday
import com.kotlin.simplebankapp.domain.member.model.vo.ProfileImage
import com.kotlin.simplebankapp.domain.member.util.GivenMember
import com.kotlin.simplebankapp.global.error.BusinessException
import com.kotlin.simplebankapp.global.error.ErrorCode
import com.kotlin.simplebankapp.global.utils.findByIdOrThrow
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("test")
internal class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
) {

    @Test
    @Transactional
    @DisplayName("회원 저장이 정상 동작한다.")
    fun saveMemberTest() {
        //given
        val memberJoinRequest = MemberJoinRequest(
            "jinwook@gmail.com",
            "1234",
            "진욱",
            "jinwook",
            LocalDate.of(1993, 6, 28),
            "Profile",
            "01031644306"
        )

        //when
        val saveId = memberService.save(memberJoinRequest)

        //then
        val findMember = memberRepository.findByIdOrThrow(saveId)
        assertThat(saveId).isEqualTo(findMember.id)
    }

    @Test
    @Transactional
    @DisplayName("회원 업데이트가 정상 동작한다.")
    fun updateMemberTest() {
        //given
        val savedMember = memberRepository.save(GivenMember.toMember())

        val memberUpdateRequest = MemberUpdateRequest(
            "kimjinwook",
            LocalDate.of(1993, 7, 28),
            "new profile",
            "김진욱"
        )

        //when
        memberService.update(memberUpdateRequest, savedMember.id)

        //then
        val findMember = memberRepository.findByIdOrThrow(savedMember.id)
        assertThat(findMember.name).isEqualTo("김진욱")
        assertThat(findMember.nickname).isEqualTo("kimjinwook")
        assertThat(findMember.profileImage).isEqualTo(ProfileImage("new profile"))
        assertThat(findMember.birth).isEqualTo(Birthday.fixture(LocalDate.of(1993, 7, 28)))
    }

    @Test
    @Transactional
    @DisplayName("회원 조회가 정상 동작한다.")
    fun methodName() {
        //given
        val memberJoinRequest = MemberJoinRequest(
            "jinwook@gmail.com",
            "1234",
            "진욱",
            "jinwook",
            LocalDate.of(1993, 6, 28),
            "Profile",
            "01031644306"
        )
        val member = memberJoinRequest.toEntity()

        memberRepository.save(member)

        //when
        val findMember = memberService.getDetails(member.id)

        //then
        assertThat(findMember.name).isEqualTo(member.name)
        assertThat(findMember.nickname).isEqualTo(member.nickname)
        assertThat(findMember.email).isEqualTo(member.email.value)
        assertThat(findMember.birth).isEqualTo(member.birth.value)
        assertThat(findMember.profileImage).isEqualTo(member.profileImage.value)
        assertThat(findMember.phoneNumber).isEqualTo(member.phoneNumber)
    }

    @Test
    @DisplayName("회원 탈퇴가 정상 동작한다.(soft delete")
    fun deleteMemberTest() {
        //given
        val memberJoinRequest = MemberJoinRequest(
            "jinwook@gmail.com",
            "1234",
            "진욱",
            "jinwook",
            LocalDate.of(1993, 6, 28),
            "Profile",
            "01031644306"
        )
        val member = memberJoinRequest.toEntity()

        val savedMember = memberRepository.save(member)

        //when
        memberService.delete(savedMember.id)

        val deletedMember = memberRepository.findByIdOrThrow(savedMember.id)

        //then
        assertThat(deletedMember.isDeleted).isTrue
    }

    @Test
    @Transactional
    @DisplayName("회원 저장 시 닉네임이 중복될 경우 예외가 발생한다. ")
    fun duplicateNicknameTest() {
        //given
        val givenMember = memberRepository.save(
            GivenMember.toMember()
        )
        val savedMember = memberRepository.save(givenMember)

        val saveMember = MemberJoinRequest(
            "jinwook@gmail.com",
            "1234",
            "진욱",
            "jinwook",
            LocalDate.of(1993, 6, 28),
            "Profile",
            "01031644306"
        )

        // when
        val message = assertThrows<BusinessException> { memberService.save(saveMember) }
            .message

        // then
        assertThat(savedMember.nickname).isEqualTo(saveMember.nickname)
        assertThat(message).isEqualTo(ErrorCode.DUPLICATE_NICKNAME.message)
    }

    @Test
    @Transactional
    @DisplayName("회원 저장 시 이메일이 중복될 경우 예외가 발생한다. ")
    fun duplicateEmailTest() {
        //given
        val givenMember = memberRepository.save(
            GivenMember.toMember()
        )
        val savedMember = memberRepository.save(givenMember)

        val saveMember = MemberJoinRequest(
            "jinwook628@gmail.com",
            "1234",
            "진욱",
            "kimjinwook",
            LocalDate.of(1993, 6, 28),
            "Profile",
            "01031644306"
        )

        // when
        val message = assertThrows<BusinessException> { memberService.save(saveMember) }
            .message

        // then
        assertThat(savedMember.email.value).isEqualTo(saveMember.email)
        assertThat(message).isEqualTo(ErrorCode.DUPLICATE_EMAIL.message)
    }

    @Test
    @DisplayName("회원 업데이트 시 닉네임이 중복되면 예외가 발생한다.")
    fun updateMemberCheckNickname() {
        //given
        val member = GivenMember.toMember()
        member.nickname = "kimjinwook"

        val savedMember = memberRepository.save(member)

        val memberUpdateRequest = MemberUpdateRequest(
            "kimjinwook",
            LocalDate.of(1993, 7, 28),
            "new profile",
            "김진욱"
        )

        // when
        val message = assertThrows<BusinessException> { memberService.update(memberUpdateRequest, savedMember.id) }
            .message

        //then
        assertThat(message).isEqualTo(ErrorCode.DUPLICATE_NICKNAME.message)
    }


}
