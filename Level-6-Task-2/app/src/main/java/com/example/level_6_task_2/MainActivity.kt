package com.example.level_6_task_2

import NavigationRockPaper
import RockPaperScissorBottomNavigation
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.level_6_task_2.ui.theme.Level6Task2Theme
import com.example.level_6_task_2.viewModel.GameViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level6Task2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: GameViewModel = viewModel()
    Scaffold(
        bottomBar = { RockPaperScissorBottomNavigation(navController) }
    ) {
        NavigationRockPaper(navController, viewModel)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}