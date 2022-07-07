package com.example.userlogindemo.Model.Login.Register

data class RegisterRequestBody(
    val id: Int = 0,
    val provider_app_id: Int = 0,
    val login_type: String,
    val last_name: String? = null,
    val first_name: String? = null,
    val password: String,
    val confirm_password: String,
    val email: String,
    val username: String,
)
