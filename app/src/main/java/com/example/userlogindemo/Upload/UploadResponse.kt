package com.example.userlogindemo.Model.Login.Register.Upload

data class UploadResponse(
    val code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean
)