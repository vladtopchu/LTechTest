package com.ltech.test.domain.repository

import com.ltech.test.domain.model.Auth
import com.ltech.test.domain.model.PhoneMask
import com.ltech.test.domain.model.Post
import com.ltech.test.domain.model.UserData

interface LTechRepository {
    suspend fun getPhoneMask(): PhoneMask
    suspend fun proceedAuth(phone: String, password: String): Auth
    suspend fun getPosts(): List<Post>

    suspend fun getUserData(): UserData
    suspend fun saveUserData(phone: String, password: String)
}