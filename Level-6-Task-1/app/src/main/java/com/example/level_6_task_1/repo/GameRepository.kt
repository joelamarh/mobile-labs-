package com.example.level_6_task_1.repo

import android.content.Context
import com.example.level_6_task_1.data.Game
import com.example.level_6_task_1.database.GameDao
import com.example.level_6_task_1.database.GameRoomDatabase

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun insert(game: Game) = gameDao.insert(game)

    suspend fun delete(game: Game) = gameDao.delete(game)

    fun getGames() = gameDao.getGames()

    suspend fun deleteAll() = gameDao.deleteAll()

}