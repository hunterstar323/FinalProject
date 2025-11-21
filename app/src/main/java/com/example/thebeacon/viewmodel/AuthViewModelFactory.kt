package com.example.thebeacon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thebeacon.data.remote.api.RetrofitClient
import com.example.thebeacon.data.remote.api.AuthService
import com.example.thebeacon.data.repository.AuthRepository

class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val api = RetrofitClient.instance.create(AuthService::class.java)
        val repo = AuthRepository(api)
        return AuthViewModel(repo) as T
    }
}
