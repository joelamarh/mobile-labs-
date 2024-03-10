package nl.pdik.level5.task2.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.level_5_task_2.R
import com.example.level_5_task_2.model.Quest
import com.example.level_5_task_2.screens.Screens
import com.example.level_5_task_2.viewmodel.QuestViewModel

import java.util.ArrayList


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestScreen(viewModel: QuestViewModel, navController: NavController) {
    val context = LocalContext.current
    val selected = remember { mutableStateOf("") }

    val quests by viewModel.quests.observeAsState(initial = emptyList())
    val errorMsg by viewModel.errorText.observeAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.getQuests()
    }

    val currentQuestIndex = remember { mutableStateOf(0) }

    errorMsg?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    val currentQuest = quests.getOrNull(currentQuestIndex.value)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        },
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (currentQuest != null) {
                Text(
                    text = stringResource(
                        id = R.string.amount_quests,
                        currentQuestIndex.value + 1,
                        quests.size
                    ),
                    modifier = Modifier.padding(all = 16.dp)
                )
                Choices(quest = currentQuest, selected = selected)

                Button(
                    onClick = {
                        if (selected.value == currentQuest.correctAnswer) {
                            currentQuestIndex.value++
                            if (currentQuestIndex.value >= quests.size) {
                                navController.navigate(Screens.HomeScreen.route)
                            }
                        } else {
                            informUser(context, R.string.not_correct)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = stringResource(id = R.string.submit))
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (quests.isEmpty()) {
                        CircularProgressIndicator()
                    } else {
                    }
                }
            }
        }
    }
}
private fun informUser(context: Context, msgId: Int) {
    Toast.makeText(context, context.getString(msgId), Toast.LENGTH_SHORT).show()
}

@Composable
fun Choices(
    quest: Quest,
    selected: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        val displayQuestion = quest.question ?: "Question not available"
        Text(text = displayQuestion, style = MaterialTheme.typography.h6) // Add styling for better visibility

        Spacer(modifier = Modifier.height(24.dp)) // Add some space before listing choices


        if (!quest.choices.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
            ) {
                items(quest.choices!!) { choice ->
                    ChoiceRow(choice = choice, selected = selected)
                }
            }
        } else {
            Text(text = "No choices available", style = MaterialTheme.typography.body2) // Placeholder when no choices
        }
    }
}

@Composable
fun ChoiceRow(choice: String, selected: MutableState<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Makes the row use the full width, making the touch area larger
            .padding(vertical = 8.dp)
            .clickable { selected.value = choice }, // Updates the selected choice on tap
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = choice == selected.value,
            onClick = { selected.value = choice } // Update choice when radio button is clicked
        )
        Text(
            text = choice,
            style = MaterialTheme.typography.body1, // Style for better text visibility
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}
