package com.example.level_3_task_1.screens

import androidx.compose.foundation.layout.*
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
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.level_3_task_1.R
import com.example.level_3_task_1.viewModel.GameViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameRatingScreens(navController: NavHostController, viewModel: GameViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        },
        content = {padding -> ScreenContent(Modifier.padding(padding), navController, viewModel)  }
    )
}


@Composable
private fun ScreenContent(modifier: Modifier,navController: NavHostController, viewModel: GameViewModel){
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = stringResource(id = R.string.rate_game), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        Text(text = viewModel.randomlyChosenGame.value, style = MaterialTheme.typography.labelMedium)
        RatingBar(value = viewModel.gameRatingAccordingToUser.value,
            config =  RatingBarConfig().stepSize(StepSize.HALF).style(RatingBarStyle.HighLighted),
            onValueChange = {viewModel.gameRatingAccordingToUser.value = it},
            onRatingChanged = {viewModel.gameRatingAccordingToUser.value = it}
        )
        Button(onClick = {
            navController.navigate(GameRatingScreens.SummaryScreen.name)
        }) {
            Text(text = stringResource(id = R.string.to_summary))
        }
    }
}

