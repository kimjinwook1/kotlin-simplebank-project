package com.kotlin.simplebankapp.domain.member.model.vo

import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ProfileImage(
        @JsonValue
        @Column(name = "profile_image_url")
        val value: String
) {

    fun validateImagePath(profileImageUrl: ProfileImage): Boolean {
        val regex = "([A-Za-z]*:\\/\\/)?\\S*".toRegex()
        return regex.containsMatchIn(profileImageUrl.value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProfileImage

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
