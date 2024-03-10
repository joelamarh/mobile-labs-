package com.example.level_4_task_2.screens

const val MOVIE_ID = "MOVIE_ID"
sealed class MovieScreens(val route: String) {
    object OverviewScreen : MovieScreens("overview")
    object DetialScreen : MovieScreens("movie/{$MOVIE_ID}")

}