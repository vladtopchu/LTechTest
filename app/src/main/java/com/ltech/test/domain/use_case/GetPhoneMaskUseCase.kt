package com.ltech.test.domain.use_case

import com.ltech.test.domain.model.PhoneMask
import com.ltech.test.domain.repository.LTechRepository
import com.ltech.test.utils.Constants
import com.ltech.test.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhoneMaskUseCase @Inject constructor(
    private val repository: LTechRepository
) {
    operator fun invoke(): Flow<Resource<PhoneMask>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getPhoneMask().toModel()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            val message = e.localizedMessage ?: Constants.HTTP_ERROR_MESSAGE
            emit(Resource.Error(message))
        } catch (e: IOException) {
            val message = e.localizedMessage ?: Constants.IO_ERROR_MESSAGE
            emit(Resource.Error(message))
        }
    }
}