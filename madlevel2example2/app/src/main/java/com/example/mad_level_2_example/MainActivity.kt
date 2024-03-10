package com.example.mad_level_2_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mad_level_2_example.ui.theme.Madlevel2exampleTheme
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Madlevel2exampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RollDiceScreen()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RollDiceScreen() {
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
        content = { innerPadding -> ScreenContent(Modifier.padding(innerPadding)) }
    )
}

private fun getRandomNumber(): Int {
    return (1..6).random()
}

private fun roundOffDecimal(number: Double): Double {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.CEILING
    return df.format(number).toDouble()
}

// Dice value is a number between 1 and 6, including. So return the appropriate array element.
private fun diceValueToImageId(diceValue: Int): Int {
    val diceValues = arrayOf(
        R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
        R.drawable.dice4, R.drawable.dice5, R.drawable.dice6
    )
    return diceValues[diceValue - 1]
}


@Composable
private fun ScreenContent(modifier: Modifier) {
    var currentDiceValue: Int by remember { mutableStateOf(getRandomNumber()) }
    var nrRolls: Int by remember { mutableStateOf(1) }
    var totalOfRolledDiceValues: Int by remember { mutableStateOf(currentDiceValue) }
    val averageDiceValueRounded = roundOffDecimal(totalOfRolledDiceValues / nrRolls.toDouble())
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 80.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Instruction()
        ImageToDisplay(currentDiceValue)
        AverageDiceValue(nrRolls,averageDiceValueRounded)
        NextRoll(
            updateDice = { newDiceint: Int
                -> currentDiceValue = newDiceint
                nrRolls++
                totalOfRolledDiceValues += newDiceint
            }
        )
    }
}

@Composable
private fun Instruction() {
    Text(text = stringResource(id = R.string.instruction))
}

@Composable
private fun ImageToDisplay(diceValue: Int) {
    Image(
        painter = painterResource(diceValueToImageId(diceValue)),
        contentDescription = "dice",
        modifier = Modifier
            .padding(all = 64.dp)
            .width(250.dp)
            .height(250.dp)
    )
}

@Composable
private fun AverageDiceValue(rollAmount: Int, averageDiceValue: Double) {
    Text(
        text = stringResource(
            R.string.average,
            averageDiceValue.toString(),
            rollAmount),
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun NextRoll(updateDice: (Int) -> Unit) {
    Button(
        modifier = Modifier
            .padding(12.dp),
        onClick = {
            updateDice(getRandomNumber())
        }
    ) {
        Text(text = stringResource(R.string.next_roll))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Madlevel2exampleTheme {
        RollDiceScreen()
    }
}