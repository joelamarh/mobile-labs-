package com.example.level_4_task_1.screens


import CatRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.level_4_task_1.data.api.utils.Resource
import com.example.level_4_task_1.data.model.Cat
import kotlinx.coroutines.launch

class CatsViewModel(application: Application) : AndroidViewModel(application) {
    private val catRepository = CatRepository()


    val catResource: LiveData<Resource<Cat>>
        get() = _catResource

    private val _catResource: MutableLiveData<Resource<Cat>> = MutableLiveData(Resource.Empty())


    fun getCat() {
        _catResource.value = Resource.Loading()
        viewModelScope.launch {
            _catResource.value = catRepository.getRandomCat();
        }
    }
}