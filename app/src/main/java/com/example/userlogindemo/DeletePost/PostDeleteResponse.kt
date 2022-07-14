package com.example.userlogindemo.DeletePost

data class PostDeleteResponse(
    val code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean
)