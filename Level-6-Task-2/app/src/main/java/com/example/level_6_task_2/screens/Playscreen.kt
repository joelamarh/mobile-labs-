import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.level_6_task_2.R
import com.example.level_6_task_2.models.Game
import com.example.level_6_task_2.models.GameMove
import com.example.level_6_task_2.models.GameResult
import com.example.level_6_task_2.ui.theme.Level6Task2Theme
import com.example.level_6_task_2.utils.Utils
import com.example.level_6_task_2.viewModel.GameViewModel
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun PlayScreen(navController: NavController, viewModel: GameViewModel) {
    val PlayerMove = remember { mutableStateOf<GameMove?>(null) }
    val ComputerMove = remember { mutableStateOf<GameMove?>(null) }
    val gamePlayed = remember { mutableStateOf<Game?>(null) }

    val context = LocalContext.current;
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text =
                        stringResource(id = R.string.app_name) + " - " +
                                stringResource(
                                    id = R.string.title
                                )
                    )
                }
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderView(viewModel)
            GameOutCome(
                gamePlayed.value,
                context = context
            )
            Row(
                Modifier.padding(top = 24.dp)
            ) {
                RockPaperScissorButtons(
                    makeMove = { gameMove: GameMove ->
                        //Lets do the computers move
                        ComputerMove.value = getComputerMove()
                        PlayerMove.value = gameMove;
                        //The GameLogic with the game played
                        gamePlayed.value = GameLogic(
                            PlayerMove.value!!,
                            ComputerMove.value!!
                        )
                        if (gamePlayed.value != null) gamePlayed.value?.let { game ->
                            viewModel.insertGame(
                                game
                            )
                        }
                    },

                    )
            }
        }

    }
}

@Composable

private fun HeaderView(viewModel: GameViewModel) {

    val wins by viewModel.wins.observeAsState()
    val loses by viewModel.loses.observeAsState()
    val draws by viewModel.draws.observeAsState()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = R.string.title), style = MaterialTheme.typography.h4)
        Text(
            text = stringResource(id = R.string.instructions),
            Modifier.padding(12.dp),
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = stringResource(id = R.string.your_stats),
            Modifier.padding(12.dp),
            style = MaterialTheme.typography.overline,
        )

        if (wins != null && draws != null && loses != null) {
            Column(
                Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = stringResource(id = R.string.wins, wins!!),
                        Modifier.padding(4.dp),
                        style = MaterialTheme.typography.overline,
                    )
                    Text(
                        text = stringResource(id = R.string.draws, draws!!),
                        Modifier.padding(4.dp),
                        style = MaterialTheme.typography.overline,
                    )
                    Text(
                        text = stringResource(id = R.string.loses, loses!!),
                        Modifier.padding(4.dp),
                        style = MaterialTheme.typography.overline,
                    )
                }

            }
        }


    }

}

private fun getStringByIdName(context: Context, name: String): String {
    val res: Resources = context.resources;
    return res.getString(res.getIdentifier(name, "string", context.packageName));
}

@Composable
fun GameOutCome(game: Game?, context: Context, showDate: Boolean = false) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (game != null) {

            Text(
                text = getStringByIdName(context, game.result.name.toString().lowercase()),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            if (showDate) {
                Text(
                    text =  Utils.dateToString(game. played),
                    style = MaterialTheme.typography.overline,
                    fontWeight = FontWeight.Bold
                )
            }

            Row() {
                Column() {
                    Card(
                        backgroundColor = Color.Blue
                    ) {
                        Image(
                            painterResource(game.computer_move.toVector()),
                            contentDescription = getStringByIdName(
                                context,
                                game.computer_move.name.toString().lowercase()
                            ),
                        )
                    }
                    Text(text = stringResource(id = R.string.computer))
                }

                Text(text = stringResource(id = R.string.vs))

                Column() {
                    Card(
                        backgroundColor = Color.Blue
                    ) {
                        Image(
                            painterResource(game.player_move.toVector()),
                            contentDescription = getStringByIdName(
                                context,
                                game.player_move.name.toString().lowercase()
                            ),
                        )
                    }
                    Text(text = stringResource(id = R.string.you))
                }

            }

        } else {
            Text(
                text = stringResource(id = R.string.draw),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RockPaperScissorButtons(makeMove: (GameMove) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                backgroundColor = Color.Gray,
                onClick = {
                    makeMove(GameMove.ROCK)
                }
            ) {
                Image(
                    painterResource(R.drawable.rock),
                    contentDescription = stringResource(id = R.string.rock)
                )
            }
            Card(
                backgroundColor = Color.Gray,
                onClick = {
                    makeMove(GameMove.PAPER)
                }
            ) {
                Image(
                    painterResource(R.drawable.paper),
                    contentDescription = stringResource(id = R.string.paper)
                )
            }

            Card(
                backgroundColor = Color.Gray,
                onClick = {
                    makeMove(GameMove.SCISSORS)
                }
            ) {
                Image(
                    painterResource(R.drawable.scissors),
                    contentDescription = stringResource(id = R.string.scissors)
                )
            }
        }

    }
}


private fun getComputerMove(): GameMove {
    val moves = GameMove.values();
    val randomIndex = (moves.indices).random()
    return moves[randomIndex]
}

private fun GameLogic(playerMove: GameMove, computerMove: GameMove): Game? {
    val dateTime = Date()
    if (playerMove in GameMove.values()) {
        if (playerMove == computerMove) {
            return Game(GameResult.DRAW, computerMove, playerMove, dateTime)
        } else if ((playerMove == GameMove.ROCK && computerMove == GameMove.SCISSORS) ||
            (playerMove == GameMove.PAPER && computerMove == GameMove.ROCK) ||
            (playerMove == GameMove.SCISSORS && computerMove == GameMove.PAPER)
        ) {
            return Game(GameResult.WIN, computerMove, playerMove, dateTime)
        } else {
            return Game(GameResult.LOSE, computerMove, playerMove, dateTime)
        }
    } else {
        println("Invalid move.")
    }
    return null;
}


