package com.example.level_3_task_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level_3_task_2.screens.PortalOverviewScreen
import com.example.level_3_task_2.screens.addPortal
import com.example.level_3_task_2.screens.portalScreens
import com.example.level_3_task_2.ui.theme.Level3Task2Theme
import com.example.level_3_task_2.viewModel.PoralView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level3Task2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    PortalNavHost(navController,modifier = Modifier)
                }
            }
        }
    }
}

    @Composable
    private fun PortalNavHost(
        navController: NavHostController, modifier: Modifier
    ) {
        val viewModel: PoralView = viewModel()
        NavHost(
            navController = navController,
            startDestination = portalScreens.portalOverview.name,
            modifier = modifier
        ){
            composable(route = portalScreens.AddPortal.name)
            {
                addPortal(navController,viewModel)
            }
            composable(portalScreens.portalOverview.name) {
                PortalOverviewScreen(navHostController = navController,
                    viewModel = viewModel)
            }
        }
    }
