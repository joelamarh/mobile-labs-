package com.example.level5example.screens

sealed class Screen(
    val route: String
) {
    object HomeScreen: Screen("home_screen")
    object CreateQuizScreen: Screen("create_quiz_screen")
    object PlayQuizScreen: Screen("play_quiz_screen")
}
