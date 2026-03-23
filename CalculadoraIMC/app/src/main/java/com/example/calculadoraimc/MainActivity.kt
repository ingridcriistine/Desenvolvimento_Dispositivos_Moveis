package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
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

    fun goNextActivity(view: View) {
        val intent = Intent(this, ResultActivity::class.java)

        val paciente = User (
            nome = findViewById<EditText>(R.id.nomeEditText).text.toString(),
            peso = findViewById<EditText>(R.id.pesoEditText).text.toString().toDouble(),
            altura = findViewById<EditText>(R.id.alturaEditText).text.toString().toDouble(),
            imc = 0.0
        )

        intent.putExtra("paciente", paciente)

        startActivity(intent)
    }
}