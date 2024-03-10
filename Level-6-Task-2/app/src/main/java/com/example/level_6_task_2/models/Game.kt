package com.example.level_6_task_2.models

import android.icu.text.DateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "game")
data class Game(

    @ColumnInfo(name = "result")
    var result: GameResult,

    @ColumnInfo(name = "computer_move")
    var computer_move: GameMove,

    @ColumnInfo(name = "player_move")
    var player_move: GameMove,

    @ColumnInfo(name = "played")
    var played: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

)