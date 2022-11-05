package com.kotlin.simplebankapp.domain.account.model

import com.kotlin.simplebankapp.domain.member.util.GivenAccount
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class AccountTest {

    @Test
    @DisplayName("계좌 엔티티 생성이 정상 동작한다.")
    fun makeAccountEntity() {
        //given
        val account = Account(
            "01031644306",
            "1234",
            "jinwook",
            0,
            false,
            "신한",
            1L,
            110364795077
        )
        //then
        assertThat(account).isNotNull
    }

    @Test
    @DisplayName("계좌 금액이 정상적으로 증가 한다.")
    fun plusMoneyTest() {
        //given
        val money = 10000L
        val account = GivenAccount.toAccount()
        val accountAmount = account.balance

        //when
        account.plusMoney(money)

        //then
        assertThat(account.balance).isEqualTo(accountAmount + money)
    }

    @Test
    @DisplayName("계좌 금액이 정상적으로 감소 한다.")
    fun minusMoneyTest() {
        //given
        val money = 10000L
        val account = GivenAccount.toAccount()
        val accountAmount = account.balance

        //when
        account.minusMoney(money)

        //then
        assertThat(account.balance).isEqualTo(accountAmount - money)
    }

    @Test
    @DisplayName("계좌 금액은 0 미만이 될 수 없다.")
    fun cantBeZero() {
        //given
        val money = 20000L
        val account = GivenAccount.toAccount()

        //then
        val message = assertThrows<IllegalArgumentException> {
            account.minusMoney(money)
        }.message

        assertThat(message).isEqualTo("잔액이 부족합니다.")
    }

    @Test
    @DisplayName("계좌 비활성화가 정상적으로 동작 한다.")
    fun deletedAccount() {
        //given
        val account = GivenAccount.toAccount()

        //when
        account.deleteAccount()

        //then
        assertThat(account.deleted).isTrue
    }

    @Test
    @DisplayName("Account Equals 테스트")
    fun equalsTest() {
        //given
        val account = Account(
            "01000000000",
            "0000",
            "userName",
            0,
            false,
            "기업",
            2L,
            110364795077
        )

        //when
        val givenAccount = GivenAccount.toAccount()

        //then
        assertThat(account.account).isEqualTo(givenAccount.account)
        assertThat(account).isEqualTo(givenAccount)
    }

}
