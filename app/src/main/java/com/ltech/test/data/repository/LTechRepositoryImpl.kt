package com.ltech.test.data.repository

import com.ltech.test.data.remote.LTechApi
import com.ltech.test.data.remote.dto.AuthResponse
import com.ltech.test.data.remote.dto.PhoneMaskResponse
import com.ltech.test.data.remote.dto.PostResponse
import com.ltech.test.domain.repository.LTechRepository
import javax.inject.Inject

class LTechRepositoryImpl @Inject constructor(
    private val api: LTechApi
): LTechRepository {

    override suspend fun getPhoneMask(): PhoneMaskResponse {
        return api.getPhoneMask()
    }

    override suspend fun auth(phone: String, password: String): AuthResponse {
        return api.auth(phone, password)
    }

    override suspend fun getPosts(): List<PostResponse> {
        return api.getPosts()
    }

}