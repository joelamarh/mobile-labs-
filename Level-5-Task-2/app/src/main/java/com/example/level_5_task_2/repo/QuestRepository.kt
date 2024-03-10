package com.example.level_5_task_2.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.level_5_task_2.model.Quest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class QuestRepository {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val questCollection = firestore.collection("questions")
    private val _quests = MutableLiveData<List<Quest>>()

    val quests: LiveData<List<Quest>> = _quests
    suspend fun fetchQuests() {
        withContext(Dispatchers.IO) {
            try {
                val result = questCollection.orderBy("id").get().await()

                // Simplifying the mapping process using 'toObject'
                val fetchedQuests = result.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject<Quest>()
                }

                _quests.postValue(fetchedQuests) // Updates the LiveData value.
            } catch (e: Exception) {
                Log.e("FIRESTORE", "Error fetching quests: ", e)
                throw QuestsRetrievalError("Unable to retrieve quests: ${e.message}")
            }
        }
    }


    class QuestsRetrievalError(message: String) : Exception(message)
}