package com.example.rslang.model

data class LoginResponse(
    val message: String,
    val token: String,
    val refreshToken: String,
    val userId: String
)