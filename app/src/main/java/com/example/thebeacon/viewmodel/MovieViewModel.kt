package com.example.thebeacon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebeacon.data.remote.model.Movie
import com.example.thebeacon.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository = MovieRepository()
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun loadAllMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _movies.value = repository.getAllMovies()
            } catch (e: Exception) {
                _error.value = e.message
            }
            _isLoading.value = false
        }
    }

    fun filterByGenre(genre: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _movies.value = repository.getMoviesByGenre(genre)
            } catch (e: Exception) {
                _error.value = e.message
            }
            _isLoading.value = false
        }
    }
}
