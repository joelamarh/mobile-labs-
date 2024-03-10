package com.example.examplelevel3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.examplelevel3.R
import com.example.examplelevel3.viewmodel.RemindersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersListScreen(navController: NavHostController, viewModel: RemindersViewModel) {
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
        content = {innerPadding -> Modifier.padding(innerPadding)
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(top = 80.dp, start = 16.dp),
            ) {
                Headers()
                RemindersList(viewModel = viewModel)
            }
        },
        floatingActionButton = { RemindersListScreenFab(navController) }
    )
}

@Composable
private fun Headers() {
    Text(
        text = stringResource(R.string.reminders),
        modifier = Modifier.padding(bottom = 8.dp),
        style = MaterialTheme.typography.headlineMedium,
    )
    Text(
        text = stringResource(R.string.see_below),
        modifier = Modifier.padding(
            bottom = 16.dp
        ),
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
fun RemindersListScreenFab(navController: NavHostController) {
    FloatingActionButton(onClick = {
        navController.navigate(RemindersScreens.NewReminder.name)
    }) {
        Icon(Icons.Filled.Add, "")
    }
}

@Composable
private fun RemindersList(viewModel: RemindersViewModel) {
    LazyColumn {
        items(items = viewModel.reminders, itemContent = { item ->
            ReminderCard(item)
        })
    }
}

@Composable
private fun ReminderCard(item: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Text(
            text = item,
            Modifier.padding(16.dp)
        )
    }
}




