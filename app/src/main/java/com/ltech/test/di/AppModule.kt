package com.ltech.test.di

import com.ltech.test.data.remote.LTechApi
import com.ltech.test.data.repository.LTechRepositoryImpl
import com.ltech.test.domain.repository.LTechRepository
import com.ltech.test.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLTechApi(): LTechApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LTechApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLTechRepository(api: LTechApi): LTechRepository {
        return LTechRepositoryImpl(api)
    }


}