package com.example.level_6_task_2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.level_6_task_2.models.Game


@Dao
interface RockDao {

    @Query("SELECT * from game ORDER BY `played` DESC")
    fun getGames(): LiveData<List<Game>>

    @Query("SELECT COUNT(*) from game WHERE result == :value")
    fun count(value: String): LiveData<Int>


    @Insert
    suspend fun insert(game: Game)

    @Insert
    suspend fun insert(game: List<Game>)

    @Delete
    suspend fun delete(game: Game)

    @Query("DELETE from game")
    suspend fun deleteAll()

}