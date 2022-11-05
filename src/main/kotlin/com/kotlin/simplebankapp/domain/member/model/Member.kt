package com.kotlin.simplebankapp.domain.member.model

import com.kotlin.simplebankapp.domain.member.model.vo.Birthday
import com.kotlin.simplebankapp.domain.member.model.vo.ProfileImage
import com.kotlin.simplebankapp.domain.member.model.vo.RoleType
import com.kotlin.simplebankapp.domain.member.model.vo.UserEmail
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import javax.persistence.*

@Entity
class Member(

    var name: String,

    var nickname: String,

    @Embedded
    var birth: Birthday,

    @Embedded
    var email: UserEmail,

    @Embedded
    var profileImage: ProfileImage,

    var phoneNumber: String,

    @Enumerated(EnumType.STRING)
    var role: RoleType,

    var deleted: Boolean,

    var password: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    fun update(name: String, nickname: String, profileImage: ProfileImage, birth: Birthday) {
        this.name = name
        this.nickname = nickname
        this.profileImage = profileImage
        this.birth = birth
    }

    fun delete() {
        this.deleted = true
    }

    fun encodePassword(encoder: PasswordEncoder): Member {
        this.password = encode(encoder, password)
        return this
    }

    fun matchPassword(rawPassword: String, encoder: PasswordEncoder): Boolean =
        encoder.matches(rawPassword, this.password)

    fun changePassword(password: String, encoder: PasswordEncoder): Member {
        this.password = encode(encoder, password)
        return this
    }

    fun validateExceedNow(birth: LocalDate) = this.birth.validateExceedDate(birth)

    fun getAge(): Int = this.birth.getAge()

    fun encode(encoder: PasswordEncoder, password: String): String = encoder.encode(password)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}
