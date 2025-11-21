package com.example.thebeacon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebeacon.data.remote.model.MovieCreateRequest
import com.example.thebeacon.data.remote.model.MovieCreateResponse
import com.example.thebeacon.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MovieCreateUIState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val response: MovieCreateResponse? = null
)

class MovieCreateViewModel(
    private val repository: MovieRepository = MovieRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieCreateUIState())
    val uiState: StateFlow<MovieCreateUIState> = _uiState

    fun createMovie(title: String, genre: String, description: String) {
        viewModelScope.launch {
            _uiState.value = MovieCreateUIState(isLoading = true)

            try {
                val res = repository.addMovie(MovieCreateRequest(title, genre, description))
                _uiState.value = MovieCreateUIState(success = true, response = res)
            } catch (e: Exception) {
                _uiState.value = MovieCreateUIState(error = e.message)
            }
        }
    }

    fun reset() {
        _uiState.value = MovieCreateUIState()
    }
}
