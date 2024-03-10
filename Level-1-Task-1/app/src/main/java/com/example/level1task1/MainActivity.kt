package com.example.level1task1

import android.os.Bundle
import android.provider.CalendarContract.Reminders
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.level1task1.ui.theme.Level1Task1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level1Task1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReminderListScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api:: class)
@Composable
private fun ReminderListScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        content = { padding -> ScreenContent(Modifier.padding(padding)) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(modifier: Modifier) {
    val context = LocalContext.current

    var newReminderData by remember { mutableStateOf(String()) }
    val reminders = remember { mutableListOf<Reminder>() }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = newReminderData,
                onValueChange = { newValue -> newReminderData = newValue },
                placeholder = { Text(stringResource(R.string.new_reminder)) },
                label = { Text(stringResource(R.string.enter_new_reminder)) }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),

                onClick = {
                    if (newReminderData.isNotBlank()) {
                        reminders.add(Reminder(newReminderData))
                        newReminderData = "";
                    } else {
                        Toast.makeText(context, (R.string.no_empty_reminder), Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.add))
            }
        }

        LazyColumn() {
            items(items = reminders, itemContent = { reminder ->
                Text(
                    text = reminder.reminderData,
                    Modifier.padding(16.dp)
                )
                Divider(
                    color = Color.LightGray, modifier = Modifier.alpha(0.5f),
                    thickness = 1.dp
                )
            })
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Level1Task1Theme {
        ReminderListScreen()
    }
}