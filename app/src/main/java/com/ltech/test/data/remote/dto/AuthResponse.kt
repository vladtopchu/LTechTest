package com.ltech.test.data.remote.dto

import com.ltech.test.domain.model.Auth

data class AuthResponse(
    var success: Boolean = false
) {
    fun toModel() = Auth(success ?: false)
}