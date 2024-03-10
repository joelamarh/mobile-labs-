package com.example.level2task1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.level2task1.ui.theme.Level2Task1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level2Task1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListScreen()
                }
            }
        }
    }
}

private fun generateStatements(): ArrayList<Statement> {
    return arrayListOf(
        Statement("A 'val' and 'var' are the same.", false),
        Statement("Mobile Application Development grants 12 ECTS.", false),
        Statement("A unit in Kotlin corresponds to a void in Java.", true),
        Statement("In Kotlin 'when' replaces the 'switch' operator in Java.", true)
    )
}


@OptIn(ExperimentalMaterial3Api:: class)
@Composable
private fun ListScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },

            )
        },
        content = { padding -> ScreenContent(Modifier.padding(padding)) },
    )

}

@Composable
private fun ScreenContent(modifier: Modifier) {
    val quizStatements: MutableList<Statement> = remember { mutableStateListOf()}
    quizStatements.addAll(generateStatements())

    Column {
        Column(Modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp)) {
            QuizInstructionsHeader()
        }
        QuizStatements(
            quizStatements,
            removeQuizStatement = { statement: Statement ->
                quizStatements.remove(statement)
            },
        )
    }
}

@Composable
private fun QuizStatements(localQuizStatements: List<Statement>, removeQuizStatement: (Statement) -> Unit
) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = localQuizStatements,
            key = { key -> key.statementID },
            itemContent = { quizStatement ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = { if(quizStatement.isTrue){
                                    val messageResourceId = R.string.answer_is_true
                                    informUser(context,messageResourceId)
                                    removeQuizStatement(quizStatement)
                                } else{
                                    val messageResourceId = R.string.statement_false
                                    informUser(context,messageResourceId)
                                } },
                                onLongPress = { if(!quizStatement.isTrue){
                                    val messageResourceId = R.string.answer_is_false
                                    informUser(context,messageResourceId)
                                    removeQuizStatement(quizStatement)
                                } else{
                                    val messageResourceId = R.string.statement_false
                                    informUser(context,messageResourceId)
                                } }
                            )
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = quizStatement.statement,
                    )
                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier.alpha(0.6f),
                    thickness = 1.dp
                )
            })
    }
}

private fun informUser(context: Context, msgId: Int) {
    Toast.makeText(context, context.getString(msgId), Toast.LENGTH_SHORT).show()
}

@Composable
private fun QuizInstructionsHeader() {
    Text(
        modifier = Modifier
            .padding(16.dp),
        text = stringResource(id = R.string.quiz_instr_header),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    )
    Text(text = stringResource(id = R.string.quiz_instr_description))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Level2Task1Theme {
        ListScreen()
    }
}
