package com.example.level_6_task_2.models

import com.example.level_6_task_2.R

enum class GameMove {
    ROCK,
    PAPER,
    SCISSORS;

    fun toVector(): Int {
        if (this == GameMove.SCISSORS) {
            return R.drawable.scissors
        }
        if (this == GameMove.PAPER) {
            return R.drawable.paper
        }
        if (this == GameMove.ROCK) {
            return R.drawable.rock
        }
        return 0;
    }
}