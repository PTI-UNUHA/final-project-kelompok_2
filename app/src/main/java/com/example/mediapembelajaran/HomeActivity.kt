package com.example.mediapembelajaran

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Ambil data nama dan NIM yang dikirim dari LoginActivity
        val name = intent.getStringExtra("USER_NAME")
        val nim = intent.getStringExtra("USER_NIM")

        val btnMateri = findViewById<Button>(R.id.btnMateri)
        btnMateri.setOnClickListener {
            val intent = Intent(this, MateriActivity::class.java)
            startActivity(intent)
        }

        val btnQuiz = findViewById<Button>(R.id.btnQuiz)
        btnQuiz.setOnClickListener {
            val intent = Intent(this, QuizLevelActivity::class.java)
            // Teruskan nama dan NIM ke QuizLevelActivity jika diperlukan
            intent.putExtra("USER_NAME", name)
            intent.putExtra("USER_NIM", nim)
            startActivity(intent)
        }

        val btnSkor = findViewById<Button>(R.id.btnSkor)
        btnSkor.setOnClickListener {
            val intent = Intent(this, ScoreLevelActivity::class.java)
            // Teruskan nama dan NIM ke ScoreLevelActivity
            intent.putExtra("USER_NAME", name)
            intent.putExtra("USER_NIM", nim)
            startActivity(intent)
        }

        val btnLeaderboard = findViewById<Button>(R.id.btnLeaderboard)
        btnLeaderboard.setOnClickListener {
            val intent = Intent(this, LeaderboardLevelActivity::class.java)
            // Teruskan nama dan NIM ke LeaderboardLevelActivity jika diperlukan
            intent.putExtra("USER_NAME", name)
            intent.putExtra("USER_NIM", nim)
            startActivity(intent)
        }
    }
}
