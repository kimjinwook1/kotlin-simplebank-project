package com.kotlin.simplebankapp.domain.member.util

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.password.PasswordEncoder

class TestPasswordEncoder : PasswordEncoder {

    companion object {
        fun init() = TestPasswordEncoder()
    }

    override fun encode(rawPassword: CharSequence): String {
        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt())
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword)
    }

    override fun upgradeEncoding(encodedPassword: String): Boolean {
        return super.upgradeEncoding(encodedPassword)
    }
}
