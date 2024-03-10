package com.example.level_4_task_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level_4_task_2.screens.MovieDetailScreen
import com.example.level_4_task_2.screens.MovieScreens
import com.example.level_4_task_2.screens.OverviewScreen
import com.example.level_4_task_2.ui.theme.Level4Task2Theme
import com.example.level_4_task_2.viewmodel.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level4Task2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Level6Task2App()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Level6Task2App() {
    val navController = rememberNavController()
    Scaffold(
    ) { innerPadding ->
        NavHost(navController, innerPadding)
    }

}

@Composable
private fun NavHost(
    navController: NavHostController, innerPadding: PaddingValues
) {
    val viewModel: MoviesViewModel = viewModel();
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = MovieScreens.OverviewScreen.route,
        Modifier.padding(innerPadding)
    ) {
        composable(MovieScreens.OverviewScreen.route) { OverviewScreen(viewModel,navController) }
        composable(MovieScreens.DetialScreen.route) { MovieDetailScreen(viewModel,navController) }

    }
}

