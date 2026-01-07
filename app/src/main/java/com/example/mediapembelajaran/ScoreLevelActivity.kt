package com.example.mediapembelajaran

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ScoreLevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_level)

        val btnMudah: Button = findViewById(R.id.btnMudah)
        val btnSedang: Button = findViewById(R.id.btnSedang)
        val btnSulit: Button = findViewById(R.id.btnSulit)
        val btnHome: Button = findViewById(R.id.btnHome)

        val sharedPreferences = getSharedPreferences("MyScores", Context.MODE_PRIVATE)

        btnMudah.setOnClickListener {
            val score = sharedPreferences.getInt("EASY_SCORE", 0) // Default 0 jika belum ada skor
            val intent = Intent(this, ScoreResultActivity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL_QUESTIONS", 5) // Asumsi 5 soal untuk mudah
            startActivity(intent)
        }

        btnSedang.setOnClickListener {
            val score = sharedPreferences.getInt("MEDIUM_SCORE", 0)
            val intent = Intent(this, ScoreResult1Activity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL_QUESTIONS", 10) // Asumsi 10 soal untuk sedang
            startActivity(intent)
        }

        btnSulit.setOnClickListener {
            val score = sharedPreferences.getInt("HARD_SCORE", 0)
            val intent = Intent(this, ScoreResult2Activity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL_QUESTIONS", 15) // Asumsi 15 soal untuk sulit
            startActivity(intent)
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
