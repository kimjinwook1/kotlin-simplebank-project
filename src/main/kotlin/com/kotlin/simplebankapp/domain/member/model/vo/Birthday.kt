package com.kotlin.simplebankapp.domain.member.model.vo

import com.fasterxml.jackson.annotation.JsonValue
import com.kotlin.simplebankapp.global.exceedBirthday
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Birthday(
    @JsonValue
    @Column(name = "birth")
    val value: LocalDate
) {

    fun getAge() = LocalDate.now().year - value.year

    companion object {
        fun fixture(birth: LocalDate): Birthday {
            return validateExceedDate(birth)
        }

        private fun validateExceedDate(birth: LocalDate): Birthday {
            if (birth > LocalDate.now()) {
                exceedBirthday()
            }
            return Birthday(birth)
        }
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
