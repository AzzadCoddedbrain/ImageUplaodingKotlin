package com.example.imageupload

import okhttp3.MultipartBody

data class ImageData(
    val image: MultipartBody.Part
)