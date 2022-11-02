package com.kotlin.simplebankapp.domain.member.model.repository

import com.kotlin.simplebankapp.domain.member.model.QMember.member
import com.kotlin.simplebankapp.global.security.CustomUserDetails
import com.kotlin.simplebankapp.global.security.QCustomUserDetails
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MemberCustomRepositoryImpl(
        private val jpaQueryFactory: JPAQueryFactory
) : MemberCustomRepository {

    override fun getDetailById(memberId: Long): CustomUserDetails? {
        return jpaQueryFactory.select(
                QCustomUserDetails(
                        member.id,
                        member.email,
                        member.role
                ))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchFirst()
    }
}
