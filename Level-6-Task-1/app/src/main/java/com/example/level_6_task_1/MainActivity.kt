package com.example.level_6_task_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


import androidx.compose.material.Surface
import androidx.navigation.compose.composable


import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


import com.example.level_6_task_1.screens.AddGameScreen
import com.example.level_6_task_1.screens.Screen
import com.example.level_6_task_1.ui.theme.Level6task1Theme
import com.example.level_6_task_1.viewmodel.GameViewModel
import nl.pdik.level4.task1.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level6task1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    GameRoomNavHost(navController, Modifier);
                }
            }
        }
    }
}

@Composable
private fun GameRoomNavHost(
    navController: NavHostController, modifier: Modifier
) {
    val viewModel: GameViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {
        composable(route = Screen.HomeScreen.route)
        {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.AddGameScreen.route) {
            AddGameScreen(navController = navController, viewModel = viewModel)
        }
    }
}

