package com.example.level_3_task_1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.level_3_task_1.R
import com.example.level_3_task_1.viewModel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameRatingStartScreen(navController: NavHostController, viewModel: GameViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        },
        content = {padding -> StartScreenContent(Modifier.padding(padding), navController, viewModel)  }
    )
}



@Composable
fun StartScreenContent(modifier: Modifier, navController: NavHostController, viewModel: GameViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.click_start),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                viewModel.randomAssessableGame()
                navController.navigate(GameRatingScreens.RatingScreen.name)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.start),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

}