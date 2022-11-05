package com.kotlin.simplebankapp.domain.member.util

import com.kotlin.simplebankapp.domain.account.model.Account

object GivenAccount {

    private var phoneNumber = "01031644306"
    private var password = "1234"
    private var username = "jinwook"
    private var amount = 10000L
    private var deleted = false
    private var bankName = "신한"
    private var memberId = 1L
    private var account = 110364795077

    fun toAccount(): Account {
        return Account(
            phoneNumber,
            password,
            username,
            amount,
            deleted,
            bankName,
            memberId,
            account
        )
    }
}
