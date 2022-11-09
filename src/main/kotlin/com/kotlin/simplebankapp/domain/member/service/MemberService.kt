package com.kotlin.simplebankapp.domain.member.service

import com.kotlin.simplebankapp.domain.member.dto.MemberDetailResponse
import com.kotlin.simplebankapp.domain.member.dto.MemberJoinRequest
import com.kotlin.simplebankapp.domain.member.dto.MemberUpdateRequest
import com.kotlin.simplebankapp.domain.member.model.Member
import com.kotlin.simplebankapp.domain.member.model.MemberRepository
import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import com.kotlin.simplebankapp.global.error.ErrorCode
import com.kotlin.simplebankapp.global.utils.fail
import com.kotlin.simplebankapp.global.utils.findByIdOrThrow
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder,
) {

    fun getDetails(memberId: Long): MemberDetailResponse {
        val findMember = getMember(memberId)
        return MemberDetailResponse.fixture(findMember)
    }

    fun save(memberJoinRequest: MemberJoinRequest): Long {
        validateDuplicationEmail(memberJoinRequest.email)
        validateDuplicationNickname(memberJoinRequest.nickname)
        val savedMember = memberRepository.save(memberJoinRequest.toEntity().encodePassword(encoder))
        return savedMember.id
    }

    fun update(memberUpdateRequest: MemberUpdateRequest, memberId: Long) {
        val findMember = getMember(memberId)

        check(!findMember.isMatchedNickname(memberUpdateRequest.nickname))
        { validateDuplicationNickname(memberUpdateRequest.nickname) }

        findMember.update(
            memberUpdateRequest.name,
            memberUpdateRequest.nickname,
            memberUpdateRequest.profileImage,
            memberUpdateRequest.birthDay
        )

    }

    fun delete(memberId: Long) {
        val member = getMember(memberId)
        member.delete()
    }

    private fun getMember(memberId: Long): Member {
        return memberRepository.findByIdOrThrow(memberId)
    }

    private fun validateDuplicationNickname(nickname: String) {
        check(memberRepository.existsByNickname(nickname)) { fail(ErrorCode.DUPLICATE_NICKNAME) }
    }

    private fun validateDuplicationEmail(email: String) {
        check(memberRepository.existsByEmail(UserEmail(email))) { fail(ErrorCode.DUPLICATE_EMAIL) }
    }

}
