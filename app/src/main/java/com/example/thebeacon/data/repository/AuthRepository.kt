package com.example.thebeacon.data.repository

import com.example.thebeacon.data.remote.api.AuthService
import com.example.thebeacon.data.remote.model.LoginRequest
import com.example.thebeacon.data.remote.model.LoginResponse
import com.example.thebeacon.data.remote.model.RegisterRequest
import com.example.thebeacon.data.remote.model.RegisterResponse

class AuthRepository(private val api: AuthService) {

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return api.register(request)
    }

    suspend fun login(request: LoginRequest): LoginResponse {
        return api.login(request)
    }
}
