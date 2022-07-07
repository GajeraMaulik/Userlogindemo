package com.example.userlogindemo.Model.Login.Register.Login

data class LoginRequestBody(
    var id: Int,
    var provider_app_id: Int = 123,
    var email: String,
    var password: String,
    var login_type: String
)
