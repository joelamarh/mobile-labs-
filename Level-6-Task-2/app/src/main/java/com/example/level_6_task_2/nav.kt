import android.app.Application
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.level_6_task_2.screens.HistoryScreen
import com.example.level_6_task_2.screens.Screen
import com.example.level_6_task_2.ui.theme.Level6Task2Theme
import com.example.level_6_task_2.viewModel.GameViewModel


@Composable
fun RockPaperScissorBottomNavigation(navController: NavHostController) {
    val navItems = listOf(
        Screen.HistoryScreen,
        Screen.PlayScreen,
    )
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        navItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = screen.icon),
                        contentDescription = screen.route
                    )
                },
                label = { Text(stringResource(screen.resourceId)) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
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
fun NavigationRockPaper(navController: NavHostController, viewModel: GameViewModel) {
    NavHost(
        navController,
        startDestination = Screen.PlayScreen.route,
    ) {
        composable(Screen.PlayScreen.route) { PlayScreen(navController, viewModel) }
        composable(Screen.HistoryScreen.route) { HistoryScreen(navController, viewModel) }
    }
}

//@Composable
//fun RockPaperScissorBottomNavigationPreview() {
//    val navController = rememberNavController()
//    Level6Task2Theme {
//        RockPaperScissorBottomNavigation(navController)
//    }
//}
//
//@Composable
//fun NavigationRockPaperPreview() {
//    val navController = rememberNavController()
//    val viewModel = GameViewModel(Application()) // Adjust initialization as needed
//    Level6Task2Theme {
//        NavigationRockPaper(navController, viewModel)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun RockPaperScissorBottomNavigationPreviewDark() {
//    val navController = rememberNavController()
//    Level6Task2Theme(darkTheme = true) {
//        RockPaperScissorBottomNavigation(navController)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun NavigationRockPaperPreviewDark() {
//    val navController = rememberNavController()
//    val viewModel = GameViewModel(Application()) // Adjust initialization as needed
//    Level6Task2Theme(darkTheme = true) {
//        NavigationRockPaper(navController, viewModel)
//    }
//}