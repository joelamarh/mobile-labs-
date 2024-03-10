package com.example.madlevel4_example.Data.api

import com.example.madlevel4_example.Data.Numbers
import retrofit2.http.GET

interface NumbersApiService {

    @GET("/random/trivia?json")
    suspend fun getRandomNumber(): Numbers
}