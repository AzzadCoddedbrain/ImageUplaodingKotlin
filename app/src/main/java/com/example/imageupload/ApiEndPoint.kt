package com.example.imageupload

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiEndPoint {

    @Multipart
    @POST("/e294dddb-e78f-4e94-b71e-12825460eae3")
    suspend fun uploadFile(
        @Part images: MultipartBody.Part
//        @Part images: List<MultipartBody.Part>
    ) : ResponseBody

}
