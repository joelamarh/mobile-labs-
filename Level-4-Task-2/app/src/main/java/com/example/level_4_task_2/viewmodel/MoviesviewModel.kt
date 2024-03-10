package com.example.level_4_task_2.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.level_4_task_1.data.api.utils.Resource
import com.example.level_4_task_2.data.model.Movie
import kotlinx.coroutines.launch
import nl.pdik.level6.task2.data.repository.MovieRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()

    var selectedMovie: Movie? = null


    val moviesResource: LiveData<Resource<List<Movie>?>>
        get() = _moviesResource

    private val _moviesResource: MutableLiveData<Resource<List<Movie>?>> = MutableLiveData(Resource.Empty())


    val movieResource: LiveData<Resource<Movie>>
        get() = _movieResource

    private val _movieResource: MutableLiveData<Resource<Movie>> = MutableLiveData(Resource.Empty())


    fun getMovies(query: String) {
        if(query.isNotBlank()){
            _movieResource.value = Resource.Loading()
            viewModelScope.launch {
                _moviesResource.value = movieRepository.getMovies(query=query)
            }
        }
        _movieResource.value = Resource.Empty();
    }
    fun getMovie(id: Int) {
        _movieResource.value = Resource.Loading()
        viewModelScope.launch {
            _movieResource.value = movieRepository.getMovie(id);
        }
    }
}