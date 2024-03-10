package com.example.level_5_task_2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.level_5_task_2.model.Quest
import com.example.level_5_task_2.repo.QuestRepository
import kotlinx.coroutines.launch
import java.io.IOException

class QuestViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "FIRESTORE"
    private val questRepository: QuestRepository = QuestRepository()
    val quests: LiveData<List<Quest>> = questRepository.quests

    private val _errorText: MutableLiveData<String> = MutableLiveData()
    val errorText: MutableLiveData<String>
        get() = _errorText

    fun getQuests() {
        viewModelScope.launch {
            try {
                questRepository.fetchQuests()
            } catch (ex: QuestRepository.QuestsRetrievalError) {
                val errorMsg = "Something went wrong while retrieving the Quests"
                Log.e(TAG, ex.message ?: errorMsg)
                _errorText.value = errorMsg
            }
        }
    }


    // Centralized error handling
    private fun handleError(e: Throwable) {
        val errorMsg = e.message ?: "An unknown error occurred."
        Log.e(TAG, errorMsg, e) // Logging with the actual exception for complete stack trace
        _errorText.value = errorMsg // Notifying UI about the error
    }
}

