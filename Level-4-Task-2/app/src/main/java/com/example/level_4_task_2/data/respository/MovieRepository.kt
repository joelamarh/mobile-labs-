package nl.pdik.level6.task2.data.repository

import android.util.Log
import com.example.level_4_task_1.data.api.Api
import com.example.level_4_task_1.data.api.utils.Resource
import com.example.level_4_task_2.data.api.ApiService
import com.example.level_4_task_2.data.model.Movie
import kotlinx.coroutines.withTimeout


class MovieRepository {
    private val apiService: ApiService = Api.Client

    suspend fun getMovies(query: String): Resource<List<Movie>?> {
        val response = try {
            withTimeout(5_000) {
                apiService.getMovies(query=query).results
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }
        println(response)
        return Resource.Success(response)
    }


    suspend fun getMovie(id: Int): Resource<Movie> {
        val response = try {
            withTimeout(5_000) {
                apiService.getMovieDetails(movieId = id)
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }
}


