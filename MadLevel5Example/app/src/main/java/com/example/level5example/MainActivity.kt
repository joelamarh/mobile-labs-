package com.example.level5example

import PlayQuizScreen
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level5example.screens.CreateQuizScreen
import com.example.level5example.screens.HomeScreen
import com.example.level5example.screens.Screen
import com.example.level5example.ui.theme.Level5ExampleTheme
import com.example.level5example.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context: Context = this
            QuizApp(context)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizApp(context: Context) {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        QuizNavHost(context, navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun QuizNavHost(context: Context, navController: NavHostController, modifier: Modifier = Modifier) {

    val viewModel: QuizViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.CreateQuizScreen.route) {
            CreateQuizScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.PlayQuizScreen.route) {
            PlayQuizScreen(navController = navController, viewModel = viewModel)
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Level5ExampleTheme {
//
//    }
//}