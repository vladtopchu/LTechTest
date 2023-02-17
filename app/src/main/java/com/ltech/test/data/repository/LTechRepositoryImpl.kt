package com.ltech.test.data.repository

import com.ltech.test.data.local.LTechDatabase
import com.ltech.test.data.remote.LTechApi
import com.ltech.test.domain.model.Auth
import com.ltech.test.domain.model.PhoneMask
import com.ltech.test.domain.model.Post
import com.ltech.test.domain.model.UserData
import com.ltech.test.domain.repository.LTechRepository
import javax.inject.Inject

class LTechRepositoryImpl @Inject constructor(
    private val api: LTechApi,
    private val db: LTechDatabase
): LTechRepository {

    override suspend fun getPhoneMask(): PhoneMask {
        val phoneMaskResponse = api.getPhoneMask()
        return phoneMaskResponse.toModel()
    }

    override suspend fun proceedAuth(phone: String, password: String): Auth {
        val authResponse = api.auth(phone, password)
        return authResponse.toModel()
    }

    override suspend fun getPosts(): List<Post> {
        val postsResponse = api.getPosts()
        return postsResponse.map { el -> el.toModel() }
    }

    override suspend fun getUserData(): UserData {
        
    }

    override suspend fun saveUserData(phone: String, password: String) {

    }

}