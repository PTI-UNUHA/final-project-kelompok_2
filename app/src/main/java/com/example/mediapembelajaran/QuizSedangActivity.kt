package com.example.mediapembelajaran

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

data class DragDropQuestion(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)

class QuizSedangActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questions: List<DragDropQuestion>
    private var isAnswered = false

    private lateinit var tvQuestion: TextView
    private lateinit var dragBox: TextView
    private lateinit var code1: TextView
    private lateinit var code2: TextView
    private lateinit var code3: TextView
    private lateinit var btnNext: Button
    private lateinit var btnHome: Button
    private lateinit var btnPrev: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_drag)

        // Initialize views
        tvQuestion = findViewById(R.id.tvQuestion)
        dragBox = findViewById(R.id.dragBox)
        code1 = findViewById(R.id.code1)
        code2 = findViewById(R.id.code2)
        code3 = findViewById(R.id.code3)
        btnNext = findViewById(R.id.btnNext)
        btnHome = findViewById(R.id.btnHome)
        btnPrev = findViewById(R.id.btnPrev)


        // Set questions
        questions = getSedangQuestions()
        displayQuestion()

        // Set drag listeners
        code1.setOnLongClickListener { v -> startDrag(v) }
        code2.setOnLongClickListener { v -> startDrag(v) }
        code3.setOnLongClickListener { v -> startDrag(v) }

        // Set drop listener
        dragBox.setOnDragListener(dragListener)

        btnHome.setOnClickListener {
            val intent = Intent(this, QuizLevelActivity::class.java)
            startActivity(intent)
        }

        btnPrev.setOnClickListener{
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                displayQuestion()
                resetDropBox()
                isAnswered = false
            }
        }

        // Set next button listener
        btnNext.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
                resetDropBox()
                isAnswered = false
            } else {
                val intent = Intent(this, QuizResultActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL_QUESTIONS", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun startDrag(view: View): Boolean {
        val draggedText = (view as TextView).text
        val item = ClipData.Item(draggedText)
        val dragData = ClipData(
            draggedText,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            item
        )
        val shadowBuilder = View.DragShadowBuilder(view)
        view.startDragAndDrop(dragData, shadowBuilder, view, 0)
        view.visibility = View.INVISIBLE
        return true
    }

    private val dragListener = View.OnDragListener { v, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                v.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                v.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val droppedText = item.text.toString()
                val target = v as TextView

                if (!isAnswered) {
                    isAnswered = true
                    val question = questions[currentQuestionIndex]
                    if (droppedText == question.correctAnswer) {
                        score++
                        target.text = droppedText
                        target.setBackgroundColor(Color.GREEN)
                        Snackbar.make(v, "Jawaban Benar!", Snackbar.LENGTH_SHORT).show()
                    } else {
                        target.text = droppedText
                        target.setBackgroundColor(Color.RED)
                        Snackbar.make(v, "Jawaban Salah!", Snackbar.LENGTH_SHORT).show()
                    }
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                val view = event.localState as View
                view.post { view.visibility = View.VISIBLE }
                true
            }
            else -> false
        }
    }

    private fun displayQuestion() {
        val question = questions[currentQuestionIndex]
        tvQuestion.text = question.text
        code1.text = question.options[0]
        code2.text = question.options[1]
        code3.text = question.options[2]
        findViewById<TextView>(R.id.tvNumber).text = "${currentQuestionIndex + 1}/${questions.size}"
        findViewById<android.widget.ProgressBar>(R.id.progressBar).progress = ((currentQuestionIndex + 1) * 100) / questions.size
    }

    private fun resetDropBox() {
        dragBox.text = "DRAG & DROP\nCODE HERE"
        dragBox.setBackgroundResource(R.drawable.dashed_border)
    }

    private fun getSedangQuestions(): List<DragDropQuestion> {
        return listOf(
            DragDropQuestion(
                "Susun kode Python untuk mencetak deret angka dari 5 hingga 1 secara menurun.",
                listOf("for i in range(5, 0, -1):", "print(i)", "if i > 0:"),
                "for i in range(5, 0, -1):"
            ),
            DragDropQuestion(
                "Bagian mana dari loop 'for' yang mendefinisikan langkah iterasi?",
                listOf("for i in range(start, stop):", "for i in range(start, stop, step):", "for i in list:"),
                "for i in range(start, stop, step):"
            ),
            DragDropQuestion(
                "Lengkapi kode untuk mencetak 'Hello World'",
                listOf("print('Hello World')", "console.log('Hello World')", "System.out.println('Hello World')"),
                "print('Hello World')"
            ),
            DragDropQuestion(
                "Manakah sintaks yang benar untuk mendefinisikan sebuah fungsi di Python?",
                listOf("function myFunction():", "def myFunction():", "create myFunction():"),
                "def myFunction():"
            ),
            DragDropQuestion(
                "Potongan kode mana yang digunakan untuk mendapatkan input dari pengguna?",
                listOf("input(\"Masukkan sesuatu: \")", "get_input(\"Masukkan sesuatu: \")", "read(\"Masukkan sesuatu: \")"),
                "input(\"Masukkan sesuatu: \")"
            )
        )
    }
}
