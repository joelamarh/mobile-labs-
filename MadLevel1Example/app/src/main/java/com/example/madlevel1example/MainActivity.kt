package com.example.madlevel1example

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madlevel1example.ui.theme.MadLevel1ExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MadLevel1ExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessAnimalScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessAnimalScreen() {
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
        content = { padding -> ScreenContent(Modifier.padding(padding)) },
    )
}


@Composable
fun ScreenContent(modifier: Modifier) {
    Column(
        Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(R.string.animal_question),
            style = MaterialTheme.typography.headlineSmall,
        )
        Image(
            painter = painterResource(id = R.drawable.giraffe),
            contentDescription = "", // decorative element
            modifier = Modifier
                .padding(all = 16.dp)
                .width(250.dp)
                .height(250.dp)
        )
        InputSegment()
    }
}

fun verifyAnswer(context: Context, answerText: String) {
    var toastText = "\"" + answerText + "\""
    toastText += if (answerText.uppercase() == context.getString(R.string.giraffe_upper)) {
        context.getString(R.string.correct)
    } else {
        context.getString(R.string.incorrect)
    }
    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSegment() {
    val context = LocalContext.current
    var answerText by remember { mutableStateOf(String()) }

    Row() {
        OutlinedTextField(
            value = answerText,
            placeholder = { Text(text = stringResource(id = R.string.animal_question)) },
            onValueChange = {
                answerText = it
            },
            label = { Text(stringResource(R.string.answer_label)) }
        )
        Button(
            modifier = Modifier.padding(start = 24.dp, top = 12.dp),
            onClick = { verifyAnswer(context, answerText) }) {
            Icon(Icons.Filled.Send, "Process user input")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MadLevel1ExampleTheme {
        GuessAnimalScreen()
    }
}