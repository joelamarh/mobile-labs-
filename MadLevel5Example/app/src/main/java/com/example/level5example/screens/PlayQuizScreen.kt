import android.widget.Toast
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
import androidx.compose.runtime.livedata.observeAsState
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
fun PlayQuizScreen(navController: NavController, viewModel: QuizViewModel) {
    val context = LocalContext.current

    viewModel.getQuiz()

    val quiz by viewModel.quiz.observeAsState()
    val errorMsg by viewModel.errorText.observeAsState()

    var answer by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = context.getString(R.string.title_play_quiz),
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
                var quizQuestion = quiz?.question
                if (quizQuestion.isNullOrEmpty()) quizQuestion = ""
                Text(
                    text = quizQuestion,
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center,
                )
                OutlinedTextField(
                    value = answer,
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
                        // VERIFY ANSWER
                        if (viewModel.isAnswerCorrect(answer)) {
                            Toast.makeText(context, R.string.answer_correct, Toast.LENGTH_SHORT)
                                .show()
                            answer = ""
                            navController.popBackStack()
                        } else {
                            val msg = context.getString(R.string.answer_incorrect, quiz?.answer)
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }

                    })
                {
                    Text(text = context.getString(R.string.confirm_answer))
                }
                if (!errorMsg.isNullOrEmpty()) {
                    Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
                    viewModel.reset()
                }
            }
        })
}

