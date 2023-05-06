package com.example.imageupload

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.imageupload.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var imageUri : Uri

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri= it!!
        binding.imageView.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.picImage.setOnClickListener(){
            contract.launch("image/*")
        }


        binding.upload.setOnClickListener(){
            upload()
          //  upload(imageUri)
        }

    }

    private fun upload() {
        val filesDir  = applicationContext.filesDir
        val file = File(filesDir,"image.png")
        Log.e("TAG", "Path: - > "+imageUri)
        val inputStream = contentResolver.openInputStream(imageUri)

        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("filenames",file.name,requestBody)


        val retrfit = RetrofitClient.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            val resposne = retrfit.uploadFile(part)
            Log.e("TAG", "upload: "+resposne.toString()  )
        }

    }



    fun File.toMultipart(partName: String): MultipartBody.Part {
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), this)
        return MultipartBody.Part.createFormData(partName, name, requestFile)
    }

    fun String.toRequestBody(): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), this)
    }

}