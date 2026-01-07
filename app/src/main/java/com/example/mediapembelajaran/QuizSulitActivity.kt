package com.example.mediapembelajaran

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizSulitActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questionList: List<EssayQuestion>
    private var currentAnswer = ""
    private var isCaps = true // Default to Uppercase

    private lateinit var tvQuestion: TextView
    private lateinit var codeBox: TextView
    private lateinit var tvProgress: TextView
    private lateinit var keyboardLayout: ViewGroup

    data class EssayQuestion(val questionText: String, val codeTemplate: String, val correctAnswer: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_sulit)

        tvQuestion = findViewById(R.id.tvQuestion)
        codeBox = findViewById(R.id.codeBox)
        tvProgress = findViewById(R.id.tvProgress)
        keyboardLayout = findViewById(R.id.customKeyboard)
        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnPrev = findViewById<Button>(R.id.btnPrev)

        setupQuestions()
        displayQuestion()
        setupKeyboard()

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnNext.setOnClickListener {
            checkAnswer()
            if (currentQuestionIndex < questionList.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            } else {
                val intent = Intent(this, ScoreResult2Activity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL_QUESTIONS", questionList.size)
                startActivity(intent)
                finish()
            }
        }

        btnPrev.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                displayQuestion()
            }
        }
    }

    private fun setupQuestions() {
        questionList = listOf(
            EssayQuestion(
                "Lengkapi kode Python berikut untuk mendapatkan kombinasi dari list_data dengan perulangan 3 kali, di mana jumlah dari setiap kombinasi adalah 7.",
                "import itertools\nlist_data = [1,3,3,4]\nfor combo in itertools.product(list_data, repeat=3):\n    if _____: Ganti bagian ini\n        print(combo)",
                "sum(combo) == 7"
            ),
            EssayQuestion(
                "Lengkapi kode untuk mencari nilai maksimum dari sebuah list angka.",
                "numbers = [10, 5, 25, 8, 15]\nmax_num = numbers[0]\nfor num in numbers:\n    if num > _____: Ganti bagian ini\n        max_num = num\nprint(max_num)",
                "max_num"
            ),
            EssayQuestion(
                "Lengkapi kode untuk membalik sebuah string.",
                "text = \"hello\"\nreversed_text = \"\"\nfor char in text:\n    reversed_text = _____ + reversed_text\nprint(reversed_text)",
                "char"
            ),
            EssayQuestion(
                "Lengkapi perulangan untuk mencetak angka dari 1 hingga 5.",
                "for i in range(1, _____): Ganti bagian ini\n    print(i)",
                "6"
            ),
            EssayQuestion(
                "Lengkapi kode untuk memeriksa apakah sebuah angka adalah bilangan genap.",
                "number = 10\nif number % 2 == _____: Ganti bagian ini\n    print(\"Genap\")",
                "0"
            )
        )
    }

    private fun displayQuestion() {
        val question = questionList[currentQuestionIndex]
        tvQuestion.text = question.questionText
        currentAnswer = ""
        updateCodeBox()
        tvProgress.text = "${currentQuestionIndex + 1}/${questionList.size}"
    }

    private fun updateCodeBox() {
        val question = questionList[currentQuestionIndex]
        val displayCode = question.codeTemplate.replace("_____", currentAnswer)
        codeBox.text = displayCode
    }

    private fun setupKeyboard() {
        for (i in 0 until keyboardLayout.childCount) {
            val row = keyboardLayout.getChildAt(i) as? ViewGroup
            row?.let {
                for (j in 0 until it.childCount) {
                    val key = it.getChildAt(j) as? Button
                    key?.setOnClickListener { view ->
                        val button = view as Button
                        val keyText = button.text.toString()
                        handleKeyPress(keyText)
                    }
                }
            }
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            if (currentAnswer.isNotEmpty()) {
                currentAnswer = currentAnswer.substring(0, currentAnswer.length - 1)
                updateCodeBox()
            }
        }

        findViewById<Button>(R.id.btnCaps).setOnClickListener {
            isCaps = !isCaps
            toggleCase()
        }
    }

    private fun handleKeyPress(key: String) {
        if (currentAnswer.length < 50) { // Limit answer length
            currentAnswer += key
            updateCodeBox()
        }
    }

    private fun toggleCase() {
        for (i in 0 until keyboardLayout.childCount) {
            val row = keyboardLayout.getChildAt(i) as? ViewGroup
            row?.let {
                for (j in 0 until it.childCount) {
                    val key = it.getChildAt(j) as? Button
                    key?.let { btn ->
                        val keyText = btn.text.toString()
                        if (keyText.length == 1 && keyText.all { it.isLetter() }) {
                            btn.text = if (isCaps) keyText.toUpperCase() else keyText.toLowerCase()
                        }
                    }
                }
            }
        }
    }

    private fun checkAnswer() {
        val question = questionList[currentQuestionIndex]
        if (currentAnswer.trim().equals(question.correctAnswer, ignoreCase = true)) {
            score += 20 // Mengubah skor menjadi 20 per soal
            Toast.makeText(this, "Jawaban Benar!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Jawaban Salah. Jawaban: ${question.correctAnswer}", Toast.LENGTH_LONG).show()
        }
    }
}