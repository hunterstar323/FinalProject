package com.example.thebeacon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebeacon.data.remote.model.LoginRequest
import com.example.thebeacon.data.remote.model.RegisterRequest
import com.example.thebeacon.data.remote.model.LoginResponse
import com.example.thebeacon.data.remote.model.RegisterResponse
import com.example.thebeacon.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import android.util.Log

data class AuthUIState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val loginResponse: LoginResponse? = null,
    val registerResponse: RegisterResponse? = null
)


class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUIState())
    val uiState: StateFlow<AuthUIState> = _uiState

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUIState(isLoading = true)

            try {
                val res = repository.register(RegisterRequest(name, email, password))
                _uiState.value = AuthUIState(success = true, registerResponse = res)

            } catch (e: HttpException) {
                val rawError = e.response()?.errorBody()?.string()
                Log.e("API_ERROR", rawError ?: "Error sin cuerpo")

                _uiState.value = AuthUIState(error = rawError ?: "Error HTTP")

            } catch (e: Exception) {
                Log.e("API_ERROR", "Error inesperado", e)
                _uiState.value = AuthUIState(error = e.message ?: "Error desconocido")
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUIState(isLoading = true)

            try {
                val res = repository.login(LoginRequest(email, password))
                _uiState.value = AuthUIState(success = true, loginResponse = res)

            } catch (e: HttpException) {
                val rawError = e.response()?.errorBody()?.string()
                Log.e("API_ERROR", rawError ?: "Error sin cuerpo")

                _uiState.value = AuthUIState(error = rawError ?: "Error HTTP")

            } catch (e: Exception) {
                Log.e("API_ERROR", "Error inesperado", e)
                _uiState.value = AuthUIState(error = e.message ?: "Error desconocido")
            }
        }
    }

    fun resetState() {
        _uiState.value = AuthUIState()
    }
}
