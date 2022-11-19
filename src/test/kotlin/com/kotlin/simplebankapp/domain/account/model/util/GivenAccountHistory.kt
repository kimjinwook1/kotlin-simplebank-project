package com.kotlin.simplebankapp.domain.member.util

import com.kotlin.simplebankapp.domain.account.model.accounthistory.model.AccountHistory
import com.kotlin.simplebankapp.domain.account.model.accounthistory.vo.DepositStatus
import java.time.LocalDateTime

object GivenAccountHistory {

    private val transferStatus = DepositStatus.DEPOSIT
    private const val transferAmount = 10000L
    private const val transferDetails = "김진욱"
    private val transferDate = LocalDateTime.of(2022, 11, 7, 15, 15, 0)
    private const val accountMemberId = 1L
    private const val depositAccount = 110364795077
    private const val withdrawalAccount = 110364795077

    fun toAccountHistory(): AccountHistory {
        return AccountHistory(
            transferStatus,
            transferAmount,
            transferDetails,
            transferDate,
            accountMemberId,
            depositAccount,
            withdrawalAccount
        )
    }
}
