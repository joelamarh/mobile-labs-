package com.example.level_4_task_1.data.api

import Dog
import com.example.level_4_task_1.data.model.Cat
import retrofit2.http.GET

interface ApiService {
    @GET("/cat?json=true")
    suspend fun getRandomCat(): Cat

    @GET("/api/breeds/image/random")
    suspend fun getRandomDog(): Dog
}