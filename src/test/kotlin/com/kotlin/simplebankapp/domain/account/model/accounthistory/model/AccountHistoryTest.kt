package com.kotlin.simplebankapp.domain.account.model.accounthistory.model

import com.kotlin.simplebankapp.domain.account.model.accounthistory.vo.DepositStatus
import com.kotlin.simplebankapp.domain.member.util.GivenAccountHistory
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class AccountHistoryTest {

    @Test
    @DisplayName("TransferStatus 테스트")
    fun equalsWithdrawalStatusTest() {
        val withdrawalAccountHistory = AccountHistory(
            DepositStatus.WITHDRAWAL,
            10000L,
            "김진욱",
            LocalDateTime.of(2022, 11, 7, 15, 15, 0),
            1,
            110364795077,
            110431478379
        )

        val depositAccountHistory = AccountHistory(
            DepositStatus.DEPOSIT,
            10000L,
            "김진욱",
            LocalDateTime.of(2022, 11, 7, 15, 15, 0),
            1,
            110364795077,
            110431478379
        )

        //then
        AssertionsForInterfaceTypes.assertThat(withdrawalAccountHistory.transferStatus)
            .isEqualTo(DepositStatus.WITHDRAWAL)

        AssertionsForInterfaceTypes.assertThat(depositAccountHistory.transferStatus)
            .isEqualTo(DepositStatus.DEPOSIT)

        AssertionsForInterfaceTypes.assertThat(depositAccountHistory.transferStatus)
            .isNotEqualTo(withdrawalAccountHistory.transferStatus)

    }


    @Test
    @DisplayName("AccountHistory Equals 테스트")
    fun equalsTest() {
        //given
        val accountHistory = AccountHistory(
            DepositStatus.DEPOSIT,
            10000L,
            "김진욱",
            LocalDateTime.of(2022, 11, 7, 15, 15, 0),
            1,
            110364795077,
            110431478379
        )

        //when
        val givenAccountHistory = GivenAccountHistory.toAccountHistory()

        //then
        AssertionsForInterfaceTypes.assertThat(accountHistory.id).isEqualTo(givenAccountHistory.id)
        AssertionsForInterfaceTypes.assertThat(accountHistory).isEqualTo(givenAccountHistory)
    }

}
