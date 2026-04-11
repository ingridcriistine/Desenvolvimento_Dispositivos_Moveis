package com.example.mediaaluno

import Aluno
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcularResultado(view: View) {
        val intent = Intent(this, ResultActivity::class.java)

        val nome = findViewById<EditText>(R.id.nomeEditText).text.toString()
        val n1 = findViewById<EditText>(R.id.nota2EditText).text.toString().toDoubleOrNull() ?: 0.0
        val n2 = findViewById<EditText>(R.id.nota1EditText).text.toString().toDoubleOrNull() ?: 0.0
        val freq = findViewById<EditText>(R.id.freqEditText).text.toString().toIntOrNull() ?: 0

        if (n1 < 0 || n1 > 10 || n2 < 0 || n2 > 10) {
            Toast.makeText(this, "As notas devem ser entre 0 e 10", Toast.LENGTH_SHORT).show()
        } else if (freq < 0 || freq > 100) {
            Toast.makeText(this, "A frequência deve ser entre 0 e 100", Toast.LENGTH_SHORT).show()
        } else {
            val aluno = Aluno(nome, n1, n2, freq)
            intent.putExtra("aluno", aluno)
            startActivity(intent)
        }
    }
}