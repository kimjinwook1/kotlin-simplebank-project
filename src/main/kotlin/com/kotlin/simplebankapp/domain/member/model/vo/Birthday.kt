package com.kotlin.simplebankapp.domain.member.model.vo

import com.fasterxml.jackson.annotation.JsonValue
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Birthday(
        @JsonValue
        @Column(name = "birth")
        val value: LocalDate
) {

    fun validateExceedDate(birth: LocalDate): Boolean {
        return this.value > LocalDate.now()
    }

    fun getAge(): Int {
        return LocalDate.now().year - value.year
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Birthday

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}
