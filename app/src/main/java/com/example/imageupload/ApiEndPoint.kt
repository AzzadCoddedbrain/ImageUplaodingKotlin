package com.example.imageupload

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiEndPoint {

    @Multipart
    @POST("/image06/api/image")
    suspend fun uploadFile(
        @Part images: MultipartBody.Part
    ) : ResponseBody

    @Multipart
    @POST("/e294dddb-e78f-4e94-b71e-12825460eae3")
   suspend fun uploadImages(
        @Part filenames: List<MultipartBody.Part>
    ): ResponseBody


    @Multipart
    @POST("/sanatan/api/v1/upload_temple")
   suspend fun uploadImagesNew(
        @Part("title") title: RequestBody,
        @Part("description") desc: RequestBody,
        @Part("location") location: RequestBody,
        @Part("location_LatLng") location_LatLng: RequestBody,
        @Part("time_table") time_table: RequestBody,
        @Part filenames: List<MultipartBody.Part>
    ): ResponseBody




}
