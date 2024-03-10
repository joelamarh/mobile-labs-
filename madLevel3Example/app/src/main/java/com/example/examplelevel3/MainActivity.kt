package com.example.examplelevel3

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
import com.example.examplelevel3.screens.AddReminderScreen
import com.example.examplelevel3.screens.RemindersListScreen
import com.example.examplelevel3.screens.RemindersScreens
import com.example.examplelevel3.ui.theme.ExampleLevel3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleLevel3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RemindersNavHost(navController = navController, modifier = Modifier)
                }
            }
        }
    }
}


@Composable
private fun RemindersNavHost(
    navController: NavHostController, modifier: Modifier
) {
  NavHost(
      navController = navController,
      startDestination = RemindersScreens.RemindersList.name,
      modifier = modifier
  ){
      composable(route = RemindersScreens.RemindersList.name) {
          RemindersListScreen(navController, viewModel())
      }
      composable(route = RemindersScreens.NewReminder.name) {
          AddReminderScreen(navController, viewModel())
      }
  }
}

