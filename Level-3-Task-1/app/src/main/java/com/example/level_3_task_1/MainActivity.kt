package com.example.level_3_task_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level_3_task_1.screens.GameRatingScreens
import com.example.level_3_task_1.screens.GameRatingStartScreen
import com.example.level_3_task_1.screens.GameRatingSummaryScreen
import com.example.level_3_task_1.ui.theme.Level3task1Theme
import com.example.level_3_task_1.viewModel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level3task1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    GameRatingNavHost(navController = navController, modifier = Modifier)
                }
            }
        }
    }
}

@Composable
private fun GameRatingNavHost(
    navController: NavHostController, modifier: Modifier
) {
    val viewModel: GameViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = GameRatingScreens.StartScreen.name,
        modifier = modifier
    ){
        composable(route = GameRatingScreens.RatingScreen.name)
        {
            GameRatingScreens(navController,viewModel)
        }
        composable(GameRatingScreens.SummaryScreen.name) {
            GameRatingSummaryScreen(navController,viewModel)
        }
        composable(GameRatingScreens.StartScreen.name) {
            GameRatingStartScreen(navController, viewModel)
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview()  {
// Level3task1Theme {
// }
//}