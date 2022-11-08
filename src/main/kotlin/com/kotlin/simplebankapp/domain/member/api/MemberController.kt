package com.kotlin.simplebankapp.domain.member.api

import com.kotlin.simplebankapp.domain.member.dto.MemberDetailResponse
import com.kotlin.simplebankapp.domain.member.dto.MemberJoinRequest
import com.kotlin.simplebankapp.domain.member.dto.MemberUpdateRequest
import com.kotlin.simplebankapp.domain.member.service.MemberService
import com.kotlin.simplebankapp.global.security.CustomUserDetails
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/member")
    fun getDetails(@AuthenticationPrincipal customUserDetails: CustomUserDetails): ResponseEntity<MemberDetailResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getDetails(customUserDetails.memberId))
    }

    @PostMapping("/member")
    fun save(@Valid @RequestBody memberJoinRequest: MemberJoinRequest): ResponseEntity<Long> {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(memberJoinRequest))
    }

    @PutMapping("/member")
    fun update(
        memberUpdateRequest: MemberUpdateRequest,
        @AuthenticationPrincipal customUserDetails: CustomUserDetails
    ): ResponseEntity<Unit> {
        memberService.update(memberUpdateRequest, customUserDetails.memberId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/member")
    fun delete(@AuthenticationPrincipal customUserDetails: CustomUserDetails): ResponseEntity<Unit> {
        memberService.delete(customUserDetails.memberId)
        return ResponseEntity.ok().build()
    }

}
