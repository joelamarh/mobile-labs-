package com.example.level_4_task_2.data.api

import com.example.level_4_task_2.data.model.Movie
import com.example.level_4_task_2.data.model.MovieResponse

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZDJiNjJiYTZjNDc5ZWNiZmRiYWQxM2RlNzdiZmY1ZCIsInN1YiI6IjY1MjEzYTFhYzUwYWQyMDEyYzFkMGJmNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gBz84a3U8OS9e5WSskTdQJkW-5CR97XcCMIOZ-P8FRQ")
    @GET("3/search/movie?include_adult=false&language=en-US&page=1")
    suspend fun getMovies(
        @Path("query") query: String): MovieResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZDJiNjJiYTZjNDc5ZWNiZmRiYWQxM2RlNzdiZmY1ZCIsInN1YiI6IjY1MjEzYTFhYzUwYWQyMDEyYzFkMGJmNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gBz84a3U8OS9e5WSskTdQJkW-5CR97XcCMIOZ-P8FRQ")
    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int): Movie

}
