package com.example.zadanie1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.zadanie1.ui.theme.Zadanie1Theme

class MainActivity : AppCompatActivity() {
    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    private val questions = listOf(
        Question("Słońce jest gwiazdą znajdującą się w centrum Układu Słonecznego?", true),
        Question("Ludzie mogą oddychać pod wodą bez żadnych urządzeń wspomagających?", false),
        Question("Australia jest najmniejszym kontynentem na świecie?", true),
        Question("Woda w stanie stałym ma wyższą gęstość niż woda w stanie ciekłym?", false),
        Question("Czy Politechnika Białostocka to najlepsza uczelnia na świecie?", true)
    )

    private var currentIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Powiązanie widoków
        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)

        updateQuestion()

        // Obiekty nasłuchujące
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questions.size
            updateQuestion()
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
}