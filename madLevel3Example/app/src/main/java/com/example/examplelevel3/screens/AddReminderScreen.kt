package com.example.examplelevel3.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.examplelevel3.R
import com.example.examplelevel3.viewmodel.RemindersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderScreen(navController: NavHostController, viewModel: RemindersViewModel) {
    val context = LocalContext.current
    var newReminder by remember { mutableStateOf(String()) }
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
                    .fillMaxWidth()
                    .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newReminder,
                    // below line is used to add placeholder ("hint") for our text field.
                    placeholder = { Text(text = stringResource(id = R.string.new_reminder)) },
                    onValueChange = {
                        newReminder = it
                    },
                    label = { Text(stringResource(R.string.enter_new_reminder)) }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    onClick = {
                        val message = if (newReminder.isNotBlank()) {
                            viewModel.addReminder(newReminder = newReminder)
                            newReminder = String() // Reset reminder text
                            navController.popBackStack()//navigate(pop//"${RemindersScreens.NewReminder.name}")
                            R.string.reminder_added
                        } else {
                            R.string.no_empty_reminder
                        }
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = stringResource(R.string.add_reminder))
                }
            }
        },
    )
}