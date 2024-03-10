package nl.pdik.level6.task1.data.repository

import Dog
import android.util.Log
import com.example.level_4_task_1.data.api.Api
import com.example.level_4_task_1.data.api.ApiService
import com.example.level_4_task_1.data.api.utils.Resource
import kotlinx.coroutines.withTimeout


class DogRepository {
    private val apiService: ApiService = Api.dogsClient;
    /**
     * suspend function that calls a suspend function from the apiService call
     * @return result wrapped in our own Resource sealed class
     */
    suspend fun getRandomDog(): Resource<Dog> {

        val response = try {
            withTimeout(5_000) {
                apiService.getRandomDog()
            }
        } catch (e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }

        return Resource.Success(response)
    }
}