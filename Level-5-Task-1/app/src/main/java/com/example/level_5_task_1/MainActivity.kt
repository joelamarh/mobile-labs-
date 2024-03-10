package com.example.level_5_task_1

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level_5_task_1.screens.CreateProfile
import com.example.level_5_task_1.screens.ProfileScreen
import com.example.level_5_task_1.screens.Screens
import com.example.level_5_task_1.ui.theme.Level5task1Theme
import com.example.level_5_task_1.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level5task1Theme {
                val context: Context = this
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileApp(context)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileApp(context: Context) {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        ProfileNavHost(context, navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ProfileNavHost(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val viewModel: ProfileViewModel = viewModel();
    NavHost(
        navController = navController,
        startDestination = Screens.CreateProfile.route,
        modifier = modifier
    ) {
        composable(Screens.ProfileScreen.route) {
            ProfileScreen(viewModel)
        }
        composable(Screens.CreateProfile.route) {
            CreateProfile(navController = navController, viewModel = viewModel)
        }

    }
}