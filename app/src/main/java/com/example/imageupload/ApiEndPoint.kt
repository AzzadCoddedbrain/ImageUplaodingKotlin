package com.example.imageupload

import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiEndPoint {


    @POST("/api/v1/upload_temple")
   suspend fun uploadImagesNew(@Body req: UploadTempleRequest): ResponseBody







}
