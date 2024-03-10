
import android.util.Log
import com.example.level_4_task_1.data.api.Api
import com.example.level_4_task_1.data.api.ApiService
import com.example.level_4_task_1.data.api.utils.Resource
import com.example.level_4_task_1.data.model.Cat
import kotlinx.coroutines.withTimeout


class CatRepository {
    private val apiService: ApiService = Api.catsClient;

    /**
     * suspend function that calls a suspend function from the apiService call
     * @return result wrapped in our own Resource sealed class
     */
    suspend fun getRandomCat() : Resource<Cat> {

        val response = try {
            withTimeout(5_000) {
                apiService.getRandomCat()
            }
        } catch(e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }

        return Resource.Success(response)
    }
}