package com.ltech.test.domain.use_case

import com.ltech.test.domain.model.Auth
import com.ltech.test.domain.repository.LTechRepository
import com.ltech.test.utils.Constants.HTTP_ERROR_MESSAGE
import com.ltech.test.utils.Constants.IO_ERROR_MESSAGE
import com.ltech.test.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class AuthUseCase @Inject constructor(
    private val repository: LTechRepository
) {
    operator fun invoke(phone: String, password: String): Flow<Resource<Auth>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.proceedAuth(phone, password)
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            val message = e.localizedMessage ?: HTTP_ERROR_MESSAGE
            emit(Resource.Error(message))
        } catch (e: IOException) {
            val message = e.localizedMessage ?: IO_ERROR_MESSAGE
            emit(Resource.Error(message))
        }
    }
}