package com.kotlin.simplebankapp.domain.member.api

import com.kotlin.simplebankapp.domain.member.dto.MemberDetailResponse
import com.kotlin.simplebankapp.domain.member.dto.MemberJoinRequest
import com.kotlin.simplebankapp.domain.member.dto.MemberUpdateRequest
import com.kotlin.simplebankapp.domain.member.service.MemberService
import com.kotlin.simplebankapp.domain.member.util.GivenMember
import com.kotlin.simplebankapp.global.security.CustomUserDetails
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

internal class MemberControllerTest {

    private val memberService: MemberService = mockk()
    private val memberController = MemberController(memberService)

    @Test
    @DisplayName("회원 저장 시 상태코드 201을 반환한다.")
    fun save() {
        //given
        val requestDto = MemberJoinRequest(
            name = GivenMember.name,
            nickname = GivenMember.nickname,
            phoneNumber = GivenMember.phoneNumber,
            email = GivenMember.userEmail,
            birth = GivenMember.birthday,
            profile = GivenMember.profileImage,
            password = GivenMember.password,
        )

        every { memberService.save(any()) } returns 1L

        //when
        val saveResponse = memberController.save(requestDto)

        //then
        assertThat(saveResponse.body).isEqualTo(1L)
        assertThat(saveResponse.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    @Test
    @DisplayName("회원 상세 조회 시 상태코드 200을 반환한다.")
    fun getDetails() {
        //given
        val member = GivenMember.toMember()
        val customUserDetails = CustomUserDetails(member.id, member.email, member.role)

        val memberResponse = MemberDetailResponse.fixture(member)

        every { memberService.getDetails(any()) } returns memberResponse

        //when
        val detailResponse = memberController.getDetails(customUserDetails)

        //then
        assertThat(detailResponse.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(detailResponse.body).isEqualTo(memberResponse)
    }

    @Test
    @DisplayName("회원 업데이트 시 상태코드 200을 반환한다.")
    fun update() {
        //given
        val member = GivenMember.toMember()
        val memberUpdateRequest = MemberUpdateRequest(
            member.nickname,
            member.birth.value,
            member.profileImage.value,
            member.name
        )
        val customUserDetails = CustomUserDetails(member.id, member.email, member.role)

        every { memberService.update(memberUpdateRequest, customUserDetails.memberId) } returns Unit

        //when
        val updateResponse = memberController.update(memberUpdateRequest, customUserDetails)

        //then
        assertThat(updateResponse.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    @DisplayName("회원 삭제 시 상태코드 200을 반환한다.")
    fun delete() {
        //given
        val member = GivenMember.toMember()
        val customUserDetails = CustomUserDetails(member.id, member.email, member.role)

        every { memberService.delete(customUserDetails.memberId) } returns Unit

        //when
        val deleteResponse = memberController.delete(customUserDetails)

        //then
        assertThat(deleteResponse.statusCode).isEqualTo(HttpStatus.OK)
    }


}
