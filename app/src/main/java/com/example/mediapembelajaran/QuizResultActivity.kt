package com.example.mediapembelajaran

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btnFinish = findViewById<Button>(R.id.btnFinish)

        val score = intent.getIntExtra("SCORE", 0)
        val totalScore = intent.getIntExtra("TOTAL_QUESTIONS", 0) * 20

        tvScore.text = "$score/$totalScore"

        btnFinish.setOnClickListener {
            val intent = Intent(this, QuizLevelActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
