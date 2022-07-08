package com.example.userlogindemo.GetUser

data class UsersResponse(
    val code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean
)