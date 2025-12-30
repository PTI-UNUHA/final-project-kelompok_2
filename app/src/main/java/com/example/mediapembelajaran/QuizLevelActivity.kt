package com.example.mediapembelajaran

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizLevelActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz_level)

        val btnHome = findViewById<Button>(R.id.btnHome)

        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)
        }
        val btnQuizMudah = findViewById<Button>(R.id.btnQuizMudah)

        btnQuizMudah.setOnClickListener {
            val intent = Intent(this, QuizMudahActivity::class.java)

            startActivity(intent)
        }
        val btnQuizSedang = findViewById<Button>(R.id.btnQuizSedang)

        btnQuizSedang.setOnClickListener {
            val intent = Intent(this, QuizSedangActivity::class.java)

            startActivity(intent)
        }
        val btnQuizSulit = findViewById<Button>(R.id.btnQuizSulit)

        btnQuizSulit.setOnClickListener {
            val intent = Intent(this, QuizSulitActivity::class.java)

            startActivity(intent)
        }
    }
}