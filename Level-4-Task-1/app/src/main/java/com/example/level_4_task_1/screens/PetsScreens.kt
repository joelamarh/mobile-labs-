package com.example.level_4_task_1.screens

import androidx.annotation.StringRes
import com.example.level_4_task_1.R

sealed class PetsScreens(val route: String, @StringRes val labelResourceId: Int) {
    object CatsScreen : PetsScreens("cats", R.string.cats)
    object DogsScreen : PetsScreens("dogs", R.string.dogs)
}
