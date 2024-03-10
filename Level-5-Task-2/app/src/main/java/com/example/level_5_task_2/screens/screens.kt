package com.example.level_5_task_2.screens

sealed class Screens(
    val route: String
){
    object HomeScreen: Screens("home_screen")
    object QuestScreen: Screens("quest_screen")

}