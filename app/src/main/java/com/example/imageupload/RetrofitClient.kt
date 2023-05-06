package com.example.imageupload

import android.util.Config.DEBUG
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getInstance(): ApiEndPoint {
        return Retrofit.Builder().baseUrl(Constant.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build().create(ApiEndPoint::class.java)
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.BODY }
        )
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", " Bearer 4|BuXVVzFsEJIbb5FIMModiMbM202yKGMsn67kVcZl")
                    .build()
                return chain.proceed(newRequest)
            }
        })
        .build()

}



