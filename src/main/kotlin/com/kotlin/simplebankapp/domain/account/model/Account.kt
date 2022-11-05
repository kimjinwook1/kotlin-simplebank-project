package com.kotlin.simplebankapp.domain.account.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Account(

    var phoneNumber: String,

    var password: String,

    var username: String,

    var balance: Long = 0,

    var deleted: Boolean,

    var bankName: String,

    var memberId: Long,

    @Id
    var account: Long,
) {

    fun plusMoney(amount: Long): Account {
        this.balance += amount
        return this
    }

    fun minusMoney(amount: Long): Account {
        if (this.balance - amount < 0) {

            throw IllegalArgumentException("잔액이 부족합니다.")
        }
        this.balance -= amount
        return this
    }

    fun deleteAccount() {
        this.deleted = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (account != other.account) return false

        return true
    }

    override fun hashCode(): Int {
        return account.hashCode()
    }


}
