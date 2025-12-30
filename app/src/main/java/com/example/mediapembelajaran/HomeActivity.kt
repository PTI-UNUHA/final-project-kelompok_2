package com.example.mediapembelajaran

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val btnMateri = findViewById<Button>(R.id.btnMateri)

        btnMateri.setOnClickListener {
            val intent = Intent(this, MateriActivity::class.java)

            startActivity(intent)
        }
        val btnQuiz = findViewById<Button>(R.id.btnQuiz)

        btnQuiz.setOnClickListener {
            val intent = Intent(this, QuizLevelActivity::class.java)

            startActivity(intent)
        }
        val btnSkor = findViewById<Button>(R.id.btnSkor)

        btnSkor.setOnClickListener {
            val intent = Intent(this, SkorLevelActivity::class.java)

            startActivity(intent)
        }
        val btnLeaderboard = findViewById<Button>(R.id.btnLeaderboard)

        btnLeaderboard.setOnClickListener {
            val intent = Intent(this, LeaderboardLevelActivity::class.java)

            startActivity(intent)
        }
    }
}