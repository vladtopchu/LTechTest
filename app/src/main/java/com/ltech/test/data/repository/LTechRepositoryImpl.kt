package com.ltech.test.data.repository

import com.ltech.test.data.local.AppDao
import com.ltech.test.data.local.LTechDatabase
import com.ltech.test.data.local.UserDataEntity
import com.ltech.test.data.remote.LTechApi
import com.ltech.test.domain.model.Auth
import com.ltech.test.domain.model.PhoneMask
import com.ltech.test.domain.model.Post
import com.ltech.test.domain.model.UserData
import com.ltech.test.domain.repository.LTechRepository
import com.ltech.test.utils.Constants.HTTP_ERROR_MESSAGE
import com.ltech.test.utils.Constants.IO_ERROR_MESSAGE
import com.ltech.test.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LTechRepositoryImpl @Inject constructor(
    private val api: LTechApi,
    private val dao: AppDao
): LTechRepository {

    override fun getPhoneMask(): Flow<Resource<PhoneMask>> = flow {
        try {
            emit(Resource.Loading())
            val response = api.getPhoneMask()
            emit(Resource.Success(response.toModel()))
        } catch (e: HttpException) {
            val message = e.localizedMessage ?: HTTP_ERROR_MESSAGE
            emit(Resource.Error(message))
        } catch (e: IOException) {
            val message = e.localizedMessage ?: IO_ERROR_MESSAGE
            emit(Resource.Error(message))
        }
    }

    override fun proceedAuth(phone: String, password: String): Flow<Resource<Auth>> = flow {
        try {
            emit(Resource.Loading())
            val response = api.auth(phone, password)
            emit(Resource.Success(response.toModel()))
        } catch (e: HttpException) {
            val message = e.localizedMessage ?: HTTP_ERROR_MESSAGE
            emit(Resource.Error(message))
        } catch (e: IOException) {
            val message = e.localizedMessage ?: IO_ERROR_MESSAGE
            emit(Resource.Error(message))
        }
    }

    override fun getPosts(): Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading())
            val response = api.getPosts()
            val result = response.map { el -> el.toModel() }
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            val message = e.localizedMessage ?: HTTP_ERROR_MESSAGE
            emit(Resource.Error(message))
        } catch (e: IOException) {
            val message = e.localizedMessage ?: IO_ERROR_MESSAGE
            emit(Resource.Error(message))
        }
    }

    override suspend fun getUserData(): UserData?  {
        val response = dao.getUserData()
        return response?.toModel()
    }

    override suspend fun saveUserData(phone: String, password: String) {
        dao.saveUserData(UserDataEntity(phone, password))
    }

}