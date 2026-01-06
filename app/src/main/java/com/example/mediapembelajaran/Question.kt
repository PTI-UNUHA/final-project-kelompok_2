package com.example.mediapembelajaran

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
