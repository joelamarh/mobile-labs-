package com.example.level_6_task_2.data

import androidx.room.TypeConverter
import com.example.level_6_task_2.models.GameMove
import com.example.level_6_task_2.models.GameResult
import java.util.Date

class Converters {
    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun stringToGameResult(value: String) = enumValueOf<GameResult>(value)

    @TypeConverter
    fun fromGameResult(value: GameResult) = value.name

    @TypeConverter
    fun stringToGameMove(value: String) = enumValueOf<GameMove>(value)

    @TypeConverter
    fun fromGameMove(value: GameMove) = value.name
}