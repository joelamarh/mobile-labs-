package com.example.level2task2

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.level2task2.ui.theme.Level2Task2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Level2Task2Theme {
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


private fun generateEquation(): Equation{
    return Equation(generateRandomConjunctionString(), generateRandomConjunctionString(), generateRandomConjunctionString())
}


private fun generateRandomConjunctionString(): String {
    val trueString = "T"
    val falseString = "F"
    return  if ((0..1).random() == 0) trueString else falseString
}


@OptIn(ExperimentalMaterial3Api:: class, ExperimentalMaterial3Api::class)
@Composable
private fun ListScreen(){
    val context = LocalContext.current
    val equation = remember { mutableStateOf(Equation("-", "-", "?")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.next)) },
                icon = {Icon(Icons.Filled.ArrowForward, stringResource(id = R.string.next))},
                onClick = {  equation.value = generateEquation()})
        },

        content = { padding -> ScreenContent(Modifier.padding(padding),context = context, equation) },
    )

}


@Composable
fun ScreenContent(modifier: Modifier, context: Context, equation: MutableState<Equation>){
    Column(modifier) {
        Column() {
            Header()
            EquationValues(equation.value)
            if(!equation.value.anwser.contains("?") && !equation.value.anwser.contains("✅")){
                AnswerButtons(
                    checkEquation = {
                            check: Boolean ->

                        if(checkConjunctionTable(equation.value,check)){
                            equation.value = Equation("✅","✅","✅")
                            informUser(context = context, R.string.correct)
                        }else{
                            informUser(context = context, R.string.incorrect)
                        }
                    }
                )
            }
        }

    }
}

private fun informUser(context: Context, msgId: Int) {
    Toast.makeText(context, context.getString(msgId), Toast.LENGTH_SHORT).show()
}

@Composable
fun EquationValues(equation: Equation){
    Row(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.SpaceAround,
    ) {
        Text(text = equation.P,fontWeight = FontWeight.Bold,     modifier = Modifier.weight(1f),)
        Text(text = equation.Q,fontWeight = FontWeight.Bold,    modifier = Modifier.weight(1f),)
        Text(text = equation.anwser, fontWeight = FontWeight.Bold,     modifier = Modifier.weight(1f),)
    }
}


private fun checkConjunctionTable(equation: Equation, d: Boolean): Boolean {
    val a = equation.P.contains("T");
    val b = equation.Q.contains("T");
    val c = equation.anwser.contains("T");

    return (a && b && c) == d
}


@Composable
private fun AnswerButtons(checkEquation: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ){
        Button(
            colors = ButtonDefaults.buttonColors(Color(0xff4caf50)),
            onClick = {
                checkEquation(true)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(100.dp),
        ) {
            Text(text = stringResource(id = R.string.btn_true))
        }
        Button(
            colors = ButtonDefaults.buttonColors(Color.Red),
            onClick = {
                checkEquation(false)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(100.dp),
        ) {
            Text(text = stringResource(id = R.string.btn_false))
        }
    }

}

@Composable
private fun Header() {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.title),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    )
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Level2Task2Theme {
        ListScreen()
    }
}