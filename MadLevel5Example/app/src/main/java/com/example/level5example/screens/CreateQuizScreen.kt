package com.example.level5example.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.level5example.R
import com.example.level5example.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateQuizScreen(navController: NavController, viewModel: QuizViewModel) {
    val context = LocalContext.current

    var question by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = context.getString(R.string.title_create_quiz),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
                // "Arrow back" implementation.
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to homescreen",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Modifier.padding(innerPadding)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = context.getString(R.string.create_quiz_here),
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center,
                )
                OutlinedTextField(
                    value = question,
                    // below line is used to add placeholder ("hint") for our text field.
                    placeholder = { Text(text = stringResource(id = R.string.question)) },
                    onValueChange = {
                        question = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .padding(8.dp),
                    label = { Text(stringResource(R.string.question)) }
                )
                OutlinedTextField(
                    value = answer,
                    // below line is used to add placeholder ("hint") for our text field.
                    placeholder = { Text(text = stringResource(id = R.string.answer)) },
                    onValueChange = {
                        answer = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .padding(8.dp),
                    label = { Text(stringResource(R.string.answer)) }
                )
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        // CREATE QUIZ
                        viewModel.reset()
                        viewModel.createQuiz(question, answer)

                        question = ""
                        answer = ""
                        navController.popBackStack()
                    }) {
                    Text(text = context.getString(R.string.create))
                }
            }
        })
}
