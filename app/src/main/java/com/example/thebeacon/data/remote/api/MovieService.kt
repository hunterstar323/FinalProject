package com.example.thebeacon.data.remote.api

import com.example.thebeacon.data.remote.model.Movie
import com.example.thebeacon.data.remote.model.MovieCreateRequest
import com.example.thebeacon.data.remote.model.MovieCreateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieService {

    @GET("movies")
    suspend fun getAllMovies(): List<Movie>

    @GET("movies/by_genre")
    suspend fun getMoviesByGenre(
        @Query("genre") genre: String
    ): List<Movie>

    @POST("movies")
    suspend fun createMovie(
        @Body body: MovieCreateRequest
    ): MovieCreateResponse
}
