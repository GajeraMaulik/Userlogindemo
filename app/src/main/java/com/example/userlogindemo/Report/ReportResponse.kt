package com.example.userlogindemo.Report

data class ReportResponse(
    val code: Int,
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)