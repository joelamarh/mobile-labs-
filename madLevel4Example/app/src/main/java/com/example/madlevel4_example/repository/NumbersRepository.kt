package com.example.madlevel4_example.repository

import android.util.Log
import com.example.madlevel4_example.Data.Numbers
import com.example.madlevel4_example.Data.api.NumbersApi
import com.example.madlevel4_example.Data.api.NumbersApiService
import com.example.madlevel4_example.Data.api.util.Resource
import com.google.android.gms.tasks.Tasks.withTimeout
import kotlinx.coroutines.withTimeout

class NumbersRepository {
    private val numbersApiService: NumbersApiService = NumbersApi.createApi()

    /**
     * suspend function that calls a suspend function from the numbersApi call
     * @return result wrapped in our own Resource sealed class
     */
    suspend fun getRandomNumber(): Resource<Numbers> {

        val response = try {
            withTimeout(5_000) {
                numbersApiService.getRandomNumber()
            }
        } catch (e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }

        return Resource.Success(response)
    }
}