package com.example.level_4_task_1.screens


import Dog
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.level_4_task_1.data.api.utils.Resource
import com.example.level_4_task_1.data.model.Cat
import kotlinx.coroutines.launch
import nl.pdik.level6.task1.data.repository.DogRepository

class DogsViewModel(application: Application) : AndroidViewModel(application) {
    private val dogRepository = DogRepository()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val dogResource: LiveData<Resource<Dog>>
        get() = _dogResource

    //initialize it with an Empty type of Resource
    private val _dogResource: MutableLiveData<Resource<Dog>> = MutableLiveData(Resource.Empty())

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getDog() {
        //set resource type to loading
        _dogResource.value = Resource.Loading()

        viewModelScope.launch {
            _dogResource.value = dogRepository.getRandomDog()
        }
    }
}