package com.kotlin.simplebankapp.domain.account.model.accounthistory.model

import com.kotlin.simplebankapp.domain.account.model.accounthistory.vo.DepositStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class AccountHistory(

    @Enumerated(EnumType.STRING)
    var transferStatus: DepositStatus,

    var transferAmount: Long,

    var transferDetails: String,

    var transferDate: LocalDateTime,

    var accountMemberId: Long,

    var depositAccount: Long,

    var withdrawalAccount: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_history_id")
    val id: Long = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccountHistory

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
