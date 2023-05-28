package com.iqbaltio.jetpacksubmission.model

data class Game(
    val id: Long,
    val title: String,
    val thumbnail: String,
    val description: String,
    val genre: String,
    val platform: String,
    val developer: String
)