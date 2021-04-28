package com.example.memorygame.data

data class PlayItem(
    val player_choose: String,
    val cpu_choose: String,
    val player_score: Int,
    val cpu_score: Int,
    val winner: String?,
    val score: Int
)