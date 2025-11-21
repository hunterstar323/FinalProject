package com.example.thebeacon.data.repository

import com.example.thebeacon.data.remote.api.MovieService
import com.example.thebeacon.data.remote.api.RetrofitClient

class MovieRepository {

    private val api = RetrofitClient.instance.create(MovieService::class.java)

    suspend fun getAllMovies() = api.getAllMovies()

    suspend fun getMoviesByGenre(genre: String) = api.getMoviesByGenre(genre)
}
