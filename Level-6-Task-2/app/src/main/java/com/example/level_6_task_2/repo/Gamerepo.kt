package com.example.level_6_task_2.repo

import android.content.Context
import com.example.level_6_task_2.data.RPSDatabase
import com.example.level_6_task_2.data.RockDao
import com.example.level_6_task_2.models.Game

class GameRepository(context: Context) {
    private val RockDao: RockDao

    init {
        val database = RPSDatabase.getDatabase(context)
        RockDao = database!!.rockDao()
    }

    suspend fun insert(game: Game) = RockDao.insert(game)

    suspend fun delete(game: Game) = RockDao.delete(game)

    fun getGames() = RockDao.getGames()

    fun getWins() = RockDao.count("WIN")
    fun getDraws() = RockDao.count("DRAW")
    fun getLoses() = RockDao.count("LOSE")

    suspend fun deleteAll() = RockDao.deleteAll()

}