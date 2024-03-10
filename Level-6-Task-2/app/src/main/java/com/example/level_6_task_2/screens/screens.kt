package com.example.level_6_task_2.screens

import androidx.annotation.StringRes
import com.example.level_6_task_2.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: Int
) {

    object HistoryScreen :
        Screen("history", R.string.bottom_nav_history, R.drawable.baseline)
    object PlayScreen : Screen(
        "play", R.string.bottom_nav_play, R.drawable.gamepad
    )
}

