package com.example.level_4_task_1.data.model

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("createdAt") val creationDate: String,
    @SerializedName("url") val urlExtension: String
)