package com.example.userlogindemo.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import java.util.HashMap

class Register {

    @Entity(tableName = "UserRegister")
    data class Register1(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val provider_app_id: Int = 0,
        val login_type: String,
        val last_name: String? = null,
        val first_name: String? = null,
        val password: String,
        val confirm_password: String,
        val email: String,
        val username: String,
    ) {


    }





}
