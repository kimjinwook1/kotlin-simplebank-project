package com.kotlin.simplebankapp.domain.member.model.repository

import com.kotlin.simplebankapp.domain.member.model.Member
import com.kotlin.simplebankapp.domain.member.model.MemberRepository
import com.kotlin.simplebankapp.domain.member.util.GivenMember
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.BeforeEach
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
) {

    companion object {
        var member: Member = GivenMember.toMember()
    }

    @BeforeEach
    fun init() {
        member = memberRepository.save(GivenMember.toMember())
    }

    @Test
    @DisplayName("UserDetails 조회가 정상 동작한다.")
    @Transactional
    fun getDetailByIdTest() {

        //when
        val customUserDetails = memberRepository.getDetailById(member.id)

        //then
        assertThat(member.id).isEqualTo(customUserDetails?.memberId)
    }

}
