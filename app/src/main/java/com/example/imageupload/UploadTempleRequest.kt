package com.example.imageupload


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class UploadTempleRequest(
    val description: String,
    val filenames: ArrayList<Filename>,
    val location_LatLng: String,
    val location: String,
    val time_table: String,
    val title: String
) : Parcelable {
    @Parcelize
    data class Filename(
        val filenames: String
    ) : Parcelable
}