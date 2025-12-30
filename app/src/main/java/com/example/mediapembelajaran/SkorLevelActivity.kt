package com.example.mediapembelajaran

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SkorLevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_score_level)

        val btnHome = findViewById<Button>(R.id.btnHome)

        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)
        }
        val btnMudah = findViewById<Button>(R.id.btnMudah)

        btnMudah.setOnClickListener {
            val intent = Intent(this, SkorMudahActivity::class.java)

            startActivity(intent)
        }
        val btnSedang = findViewById<Button>(R.id.btnSedang)

        btnSedang.setOnClickListener {
            val intent = Intent(this, SkorSedangActivity::class.java)

            startActivity(intent)
        }
        val btnSulit = findViewById<Button>(R.id.btnSulit)

        btnSulit.setOnClickListener {
            val intent = Intent(this, SkorSulitActivity::class.java)

            startActivity(intent)
        }
    }
}