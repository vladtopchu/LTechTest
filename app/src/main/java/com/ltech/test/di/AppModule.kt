package com.ltech.test.di

import android.app.Application
import androidx.room.Room
import com.ltech.test.data.local.AppDao
import com.ltech.test.data.local.LTechDatabase
import com.ltech.test.data.remote.LTechApi
import com.ltech.test.data.repository.LTechRepositoryImpl
import com.ltech.test.domain.repository.LTechRepository
import com.ltech.test.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLTechDatabase(app: Application): LTechDatabase {
        return Room.databaseBuilder(
            app,
            LTechDatabase::class.java,
            "ltech_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppDao(db: LTechDatabase): AppDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideLTechApi(): LTechApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LTechApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLTechRepository(api: LTechApi, dao: AppDao): LTechRepository {
        return LTechRepositoryImpl(api, dao)
    }

}