package com.example.level_6_task_2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.level_6_task_2.models.Game
import com.example.level_6_task_2.repo.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    val gamelog = gameRepository.getGames()

    val wins = gameRepository.getWins();
    val loses = gameRepository.getLoses();
    val draws = gameRepository.getDraws();

    /**
     * Insert a game into the repository.
     */
    fun insertGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insert(game)
            }
        }
    }

    fun deleteGameLogs() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAll()
            }
        }
    }

    /**
     * Delete a game from the repository.
     */
//    fun deleteGame(game: Game) {
//        mainScope.launch {
//            withContext(Dispatchers.IO) {
//                gameRepository.delete(game)
//            }
//        }
//    }

}