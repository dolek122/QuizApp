package com.example.zadanie1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PromptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt)

        val hintTextView: TextView = findViewById(R.id.hintButton)
        val showAnswerButton: Button = findViewById(R.id.showAnswerButton)

        val correctAnswer = intent.getBooleanExtra("correctAnswer", false)

        showAnswerButton.setOnClickListener {
            hintTextView.text = "Poprawna odpowiedź: $correctAnswer"
            setResult(RESULT_OK, Intent().putExtra("answerShown", true))
            finish()
        }
    }
}
