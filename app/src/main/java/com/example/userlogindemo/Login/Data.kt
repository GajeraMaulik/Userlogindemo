package com.example.userlogindemo.Model.Login.Register.Login

data class Data(
    val about: String,
    val access_token: String,
    val app_no: String,
    val app_version: String,
    val email: String,
    val first_name: String,
    val gender: String,
    val gmt_time: String,
    val id: Int,
    val is_allow_notification: Int,
    val is_login: Int,
    val is_term_condition: Int,
    val last_name: String,
    val login_type: String,
    val profile_picture: String,
    val status: String,
    val thumbnail: String,
    val timezone: String,
    val user_category: List<UserCategory>,
    val username: String
)