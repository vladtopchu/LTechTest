package com.ltech.test.domain.repository

import com.ltech.test.data.remote.dto.AuthResponse
import com.ltech.test.data.remote.dto.PhoneMaskResponse
import com.ltech.test.data.remote.dto.PostResponse

interface LTechRepository {
    suspend fun getPhoneMask(): PhoneMaskResponse
    suspend fun auth(phone: String, password: String, ): AuthResponse
    suspend fun getPosts(): List<PostResponse>
}