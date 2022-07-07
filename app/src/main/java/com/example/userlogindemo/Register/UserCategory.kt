package com.example.userlogindemo.Model.Login.Register

data class UserCategory(
    val id: Int,
    val image: String,
    val is_selected: Boolean,
    val meta: String,
    val name: String,
    val status: String,
    val thumbnail: String
)