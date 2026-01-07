package com.example.mediapembelajaran

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizMudahActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var tvQuestion: TextView
    private lateinit var optA: TextView
    private lateinit var optB: TextView
    private lateinit var optC: TextView
    private lateinit var optD: TextView
    private lateinit var btnNext: Button
    private lateinit var tvWrong: TextView
    private lateinit var tvInfo: TextView
    private lateinit var tvProgress: TextView
    private lateinit var progressBar: ProgressBar

    private val questions = listOf(
        Question("Variabel dalam Python yang digunakan untuk menyimpan teks adalah...",
            listOf("Integer", "Float", "String", "Boolean"), 2),
        Question("Manakah dari berikut ini yang merupakan tipe data angka?",
            listOf("String", "Integer", "Boolean", "List"), 1),
        Question("Perintah untuk menampilkan output di Python adalah...",
            listOf("print()", "input()", "len()", "type()"), 0),
        Question("Struktur data yang dapat diubah (mutable) di Python adalah...",
            listOf("Tuple", "String", "List", "Integer"), 2),
        Question("Operator untuk sisa bagi (modulo) di Python adalah...",
            listOf("/", "*", "%", "+"), 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        tvQuestion = findViewById(R.id.tvQuestion)
        optA = findViewById(R.id.optA)
        optB = findViewById(R.id.optB)
        optC = findViewById(R.id.optC)
        optD = findViewById(R.id.optD)
        btnNext = findViewById(R.id.btnNext)
        tvWrong = findViewById(R.id.tvWrong)
        tvInfo = findViewById(R.id.tvInfo)
        tvProgress = findViewById(R.id.tvProgress)
        progressBar = findViewById(R.id.progressBar)
        val btnHome = findViewById<Button>(R.id.btnHome)

        displayQuestion()

        optA.setOnClickListener { checkAnswer(0) }
        optB.setOnClickListener { checkAnswer(1) }
        optC.setOnClickListener { checkAnswer(2) }
        optD.setOnClickListener { checkAnswer(3) }

        btnNext.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
            } else {
                // Kuis selesai, pindah ke layar hasil
                val intent = Intent(this, ScoreResultActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL_QUESTIONS", questions.size)
                startActivity(intent)
            }
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, QuizLevelActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayQuestion() {
        resetOptionStyles()

        val question = questions[currentQuestionIndex]
        tvQuestion.text = question.questionText
        optA.text = "A. ${question.options[0]}"
        optB.text = "B. ${question.options[1]}"
        optC.text = "C. ${question.options[2]}"
        optD.text = "D. ${question.options[3]}"

        progressBar.max = questions.size
        progressBar.progress = currentQuestionIndex + 1
        tvProgress.text = "${currentQuestionIndex + 1}/${questions.size}"

        enableOptions(true)
        tvWrong.visibility = View.GONE
        tvInfo.visibility = View.GONE
    }

    private fun checkAnswer(selectedIndex: Int) {
        enableOptions(false)
        val question = questions[currentQuestionIndex]
        if (selectedIndex == question.correctAnswerIndex) {
            score += 20
            highlightAnswer(selectedIndex, true)
        } else {
            highlightAnswer(selectedIndex, false)
            highlightAnswer(question.correctAnswerIndex, true)
            tvWrong.visibility = View.VISIBLE
        }
        tvInfo.visibility = View.VISIBLE
    }

    private fun highlightAnswer(index: Int, isCorrect: Boolean) {
        val optionView = when (index) {
            0 -> optA
            1 -> optB
            2 -> optC
            3 -> optD
            else -> null
        }
        if (isCorrect) {
            optionView?.background = ContextCompat.getDrawable(this, R.drawable.opt_true)
        } else {
            optionView?.background = ContextCompat.getDrawable(this, R.drawable.apt_false)
        }
    }

    private fun resetOptionStyles() {
        optA.background = ContextCompat.getDrawable(this, R.drawable.opt_normal)
        optB.background = ContextCompat.getDrawable(this, R.drawable.opt_normal)
        optC.background = ContextCompat.getDrawable(this, R.drawable.opt_normal)
        optD.background = ContextCompat.getDrawable(this, R.drawable.opt_normal)
    }

    private fun enableOptions(enabled: Boolean) {
        optA.isEnabled = enabled
        optB.isEnabled = enabled
        optC.isEnabled = enabled
        optD.isEnabled = enabled
    }
}
