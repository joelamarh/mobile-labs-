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
fun GameRatingSummaryScreen(navController: NavHostController, viewModel: GameViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        },
        content = {padding -> Summary(modifier = Modifier.padding(padding) , navController = navController, viewModel = viewModel)  }
    )
}


@Composable
fun Summary(modifier: Modifier, navController: NavHostController, viewModel: GameViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val resultText = stringResource(
            id = R.string.rate_result,
            viewModel.randomlyChosenGame.value,
            viewModel.gameRatingAccordingToUser.value
        )

        Text(
            text = resultText,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                navController.popBackStack(
                    route = GameRatingScreens.StartScreen.name,
                    false
                )
                viewModel.reset()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.start_over),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
        }
    }

}