package com.example.thebeacon.data.remote.api

import com.example.thebeacon.data.remote.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movies")
    suspend fun getAllMovies(): List<Movie>

    @GET("movies/genre/{genre}")
    suspend fun getMoviesByGenre(@Path("genre") genre: String): List<Movie>
}
