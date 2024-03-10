package com.example.level_5_task_2.model

import com.google.firebase.firestore.PropertyName

data class Quest(
    @PropertyName("id") var id: Int = 0,
    @PropertyName("question") var question: String? = null,
    @PropertyName("choices") var choices: ArrayList<String>? = null,
    @PropertyName("correctAnswer") var correctAnswer: String? = null
)