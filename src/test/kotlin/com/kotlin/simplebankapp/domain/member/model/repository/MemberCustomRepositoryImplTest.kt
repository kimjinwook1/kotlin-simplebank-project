package com.kotlin.simplebankapp.domain.member.model.repository

import com.kotlin.simplebankapp.domain.member.model.MemberRepository
import com.kotlin.simplebankapp.domain.member.util.GivenMember
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
internal class MemberCustomRepositoryImplTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val memberCustomRepository: MemberCustomRepository
) {

    @Test
    @DisplayName("UserDetails 조회가 정상 동작한다.")
    @Transactional
    fun getDetailByIdTest() {
        //given
        val saveMember = memberRepository.save(GivenMember.toMember())

        //when
        val customUserDetails = memberCustomRepository.getDetailById(GivenMember.toMember().id!!)

        //then
        assertThat(saveMember.id).isEqualTo(customUserDetails?.memberId)
    }

}
