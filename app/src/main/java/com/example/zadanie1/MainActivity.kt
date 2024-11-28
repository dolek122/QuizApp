package com.example.zadanie1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var hintButton: Button

    private val questions = listOf(
        Question("Słońce jest gwiazdą znajdującą się w centrum Układu Słonecznego?", true),
        Question("Ludzie mogą oddychać pod wodą bez żadnych urządzeń wspomagających?", false),
        Question("Australia jest najmniejszym kontynentem na świecie?", true),
        Question("Woda w stanie stałym ma wyższą gęstość niż woda w stanie ciekłym?", false),
        Question("Czy Politechnika Białostocka to najlepsza uczelnia na świecie?", true)
    )

    private var currentIndex = 0
    private var correctAnswers = 0

    companion object {
        const val REQUEST_CODE_PROMPT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Przywracanie stanu
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0)
            correctAnswers = savedInstanceState.getInt("correctAnswers", 0)
        }

        // Powiązanie widoków
        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        hintButton = findViewById(R.id.hintButton)

        updateQuestion()

        // Obiekty nasłuchujące
        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questions.size
            updateQuestion()
        }
        hintButton.setOnClickListener {
            val intent = Intent(this, PromptActivity::class.java)
            intent.putExtra("correctAnswer", questions[currentIndex].isCorrect)
            startActivityForResult(intent, REQUEST_CODE_PROMPT)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
        outState.putInt("correctAnswers", correctAnswers)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PROMPT && resultCode == RESULT_OK) {
            val answerShown = data?.getBooleanExtra("answerShown", false) ?: false
            if (answerShown) {
                Toast.makeText(this, "Podpowiedź została wyświetlona!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateQuestion() {
        questionText.text = questions[currentIndex].text
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questions[currentIndex].isCorrect

        if (userAnswer == correctAnswer) {
            correctAnswers++
            Toast.makeText(this, "Poprawnie!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Błąd!", Toast.LENGTH_SHORT).show()
        }

        if (currentIndex == questions.size - 1) {
            Toast.makeText(this, "Koniec quizu! Wynik: $correctAnswers/${questions.size}", Toast.LENGTH_LONG).show()
        }
    }

    // Metody cyklu życia
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called")
    }
}
