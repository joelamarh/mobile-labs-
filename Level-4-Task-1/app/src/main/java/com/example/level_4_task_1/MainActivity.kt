package com.example.level_4_task_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.level_4_task_1.ui.theme.Level4task1Theme



import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.level_4_task_1.screens.CatsScreen
import com.example.level_4_task_1.screens.DogsScreen
import com.example.level_4_task_1.screens.PetsScreens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level4task1Theme {
                Level4Task1App()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Level4Task1App() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
            )
        },
        bottomBar = {
            BottomNav(navController)
        }
    )
    { innerPadding ->
        NavHost(navController, innerPadding)
    }
}


@Composable
fun BottomNav(navController: NavController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val screens = listOf(
            PetsScreens.CatsScreen,
            PetsScreens.DogsScreen,
        )
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.Favorite, contentDescription = null,
                        tint = Color.White,
                    )
                },
                label = { Text(stringResource(screen.labelResourceId), color = Color.White) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
private fun NavHost(
    navController: NavHostController, innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = PetsScreens.CatsScreen.route,
        Modifier.padding(innerPadding)
    ){
        composable(PetsScreens.CatsScreen.route){CatsScreen()}
        composable(PetsScreens.DogsScreen.route){ DogsScreen()}

    }
}


@Composable
fun TestText(navController: NavHostController, modifier: Modifier = Modifier) {
    //TODO: we change this code later
    Text(
        modifier = Modifier
            .padding(start = 16.dp, top = 80.dp),
        text = "It works! - remove this text later")
}