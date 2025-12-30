package com.example.mediapembelajaran

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class LeaderboardLevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_leaderboard)

        // Button Home
        val btnHome = findViewById<Button>(R.id.btnHome)
        btnHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Leaderboard MUDAH
        val btnLeaderboardMudah = findViewById<LinearLayout>(R.id.btnLeaderboardMudah)
        btnLeaderboardMudah.setOnClickListener {
            startActivity(Intent(this, LeaderboardMudahActivity::class.java))
        }

        // Leaderboard SEDANG
        val btnLeaderboardSedang = findViewById<LinearLayout>(R.id.btnLeaderboardSedang)
        btnLeaderboardSedang.setOnClickListener {
            startActivity(Intent(this, LeaderboardSedangActivity::class.java))
        }

        // Leaderboard SULIT
        val btnLeaderboardSulit = findViewById<LinearLayout>(R.id.btnLeaderboardSulit)
        btnLeaderboardSulit.setOnClickListener {
            startActivity(Intent(this, LeaderboardSulitActivity::class.java))
        }
    }
}