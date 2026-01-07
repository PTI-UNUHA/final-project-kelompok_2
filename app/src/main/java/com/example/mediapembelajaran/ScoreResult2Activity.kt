package com.example.mediapembelajaran

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreResult2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_result2)

        val tvScore: TextView = findViewById(R.id.tvScore)
        val tvCongrats: TextView = findViewById(R.id.tvCongrats)
        val tvDesc: TextView = findViewById(R.id.tvDesc)
        val btnHome: Button = findViewById(R.id.btnHome)

        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)
        val totalScore = totalQuestions * 20

        tvScore.text = "$score/$totalScore"

        // Logika untuk umpan balik dan motivasi
        when (score) {
            100 -> {
                tvCongrats.text = "Luar Biasa!"
                tvDesc.text = "Kamu benar-benar menguasai materi ini! Pertahankan!"
            }
            in 80..99 -> {
                tvCongrats.text = "Bagus Sekali!"
                tvDesc.text = "Skormu nyaris sempurna. Terus tingkatkan, ya!"
            }
            in 60..79 -> {
                tvCongrats.text = "Cukup Bagus!"
                tvDesc.text = "Terus belajar dan jangan ragu untuk mencoba lagi."
            }
            else -> {
                tvCongrats.text = "Jangan Menyerah!"
                tvDesc.text = "Setiap kesalahan adalah pelajaran berharga. Ayo coba lagi!"
            }
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
