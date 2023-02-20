package com.ltech.test.domain.repository

import com.ltech.test.domain.model.Auth
import com.ltech.test.domain.model.PhoneMask
import com.ltech.test.domain.model.Post
import com.ltech.test.domain.model.UserData
import com.ltech.test.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LTechRepository {
    fun getPhoneMask(): Flow<Resource<PhoneMask>>
    fun proceedAuth(phone: String, password: String): Flow<Resource<Auth>>
    fun getPosts(): Flow<Resource<List<Post>>>
    suspend fun getUserData(): UserData?
    suspend fun saveUserData(phone: String, password: String)
}