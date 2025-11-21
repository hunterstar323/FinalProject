package com.example.thebeacon.data.remote.api

import com.example.thebeacon.data.remote.model.LoginRequest
import com.example.thebeacon.data.remote.model.LoginResponse
import com.example.thebeacon.data.remote.model.RegisterRequest
import com.example.thebeacon.data.remote.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse

    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse
}
