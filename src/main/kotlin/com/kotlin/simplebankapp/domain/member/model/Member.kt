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

    var password: String,

) {

    var isDeleted = false

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun update(name: String, nickname: String, profileImage: String, birth: LocalDate) {
        this.name = name
        this.nickname = nickname
        this.profileImage = ProfileImage(profileImage)
        this.birth = Birthday.fixture(birth)
    }

    fun delete() {
        this.isDeleted = true
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

    fun getAge(): Int = this.birth.getAge()

    fun encode(encoder: PasswordEncoder, password: String): String = encoder.encode(password)

    fun isMatchedNickname(nickname: String): Boolean {
        if (this.nickname == nickname) {
            return true
        }
        return false
    }

    companion object {
        fun fixture(
            name: String,
            nickname: String,
            birth: LocalDate,
            email: String,
            profileImage: String,
            phoneNumber: String,
            password: String,
        ): Member {
            return Member(
                name = name,
                nickname = nickname,
                birth = Birthday.fixture(birth),
                email = UserEmail(email),
                profileImage = ProfileImage(profileImage),
                phoneNumber = phoneNumber,
                password = password,
                role = RoleType.USER
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
