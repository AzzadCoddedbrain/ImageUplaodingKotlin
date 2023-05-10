package com.example.imageupload

import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.imageupload.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var imageUri: Uri
    var base64Data: String? = null

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it!!
        binding.imageView.setImageURI(it)
    }

    private fun convertImageFileToBase64(imageFile: File): String {
        return FileInputStream(imageFile).use { inputStream ->
            ByteArrayOutputStream().use { outputStream ->
                Base64OutputStream(outputStream, Base64.DEFAULT).use { base64FilterStream ->
                    inputStream.copyTo(base64FilterStream)
                    base64FilterStream.close()
                    outputStream.toString()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.picImage.setOnClickListener() {
            contract.launch("image/*")
        }

        binding.upload.setOnClickListener() {
            uploadFile()
        }

    }


    private fun uploadFile() {

        base64Data = convertImageFileToBase64(File(FileUtils.getPath(this, imageUri)))

        Log.i("TAG", "uploadFile: -> "+base64Data.toString() )

        val retrfit = RetrofitClient.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            val listOfName: ArrayList<UploadTempleRequest.Filename> = ArrayList()
            listOfName.add(UploadTempleRequest.Filename( base64Data.toString()))
            listOfName.add(UploadTempleRequest.Filename( base64Data.toString()))
            val resposne = retrfit.uploadImagesNew(UploadTempleRequest("description", listOfName,"lat","sjdhg","time table ","my title"))

        }
    }

}