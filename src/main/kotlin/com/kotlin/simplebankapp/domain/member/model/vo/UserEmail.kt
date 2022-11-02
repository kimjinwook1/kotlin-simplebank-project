package com.kotlin.simplebankapp.domain.member.model.vo

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class UserEmail(
        @JsonValue
        @Column(name = "email")
        val value: String
) {

    fun getHost(): String {
        val index = value.indexOf("@")
        return value.substring(index + 1)
    }

    fun getId(): String {
        val index = value.indexOf("@")
        return value.take(index)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEmail

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
