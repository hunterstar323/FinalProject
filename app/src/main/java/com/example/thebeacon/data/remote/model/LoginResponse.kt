package com.example.thebeacon.data.remote.model

data class LoginResponse(
    val message: String,
    val uid: String,
    val email: String,
    val idToken: String,
    val refreshToken: String
)
