package com.ltech.test.data.remote

import com.ltech.test.data.remote.dto.AuthResponse
import com.ltech.test.data.remote.dto.PhoneMaskResponse
import com.ltech.test.data.remote.dto.PostResponse
import com.ltech.test.utils.Constants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LTechApi {
    @GET("v1/phone_masks")
    suspend fun getPhoneMask(): PhoneMaskResponse

    @FormUrlEncoded
    @POST("v1/auth")
    suspend fun auth(
        @Field("phone")
        phone: String,
        @Field("password")
        password: String,
    ): AuthResponse

    @GET("v1/posts")
    suspend fun getPosts(): List<PostResponse>
}