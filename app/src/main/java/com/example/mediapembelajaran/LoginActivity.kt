package com.example.mediapembelajaran

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val inputName = findViewById<EditText>(R.id.inputName)
        val inputNim = findViewById<EditText>(R.id.inputNim)
        val btnMasuk = findViewById<Button>(R.id.btnMasuk)

        btnMasuk.setOnClickListener {
            val name = inputName.text.toString().trim()
            val nim = inputNim.text.toString().trim()

            if (name.isNotEmpty() && nim.isNotEmpty()) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("USER_NAME", name)
                intent.putExtra("USER_NIM", nim)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Nama dan NIM tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
