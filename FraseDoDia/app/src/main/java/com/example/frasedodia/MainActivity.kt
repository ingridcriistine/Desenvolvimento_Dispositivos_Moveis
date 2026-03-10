package com.example.frasedodia

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.format.TextStyle

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

    fun quote(view: View) {

        val quotes = arrayOf(
            "Hoje está chovendo",
            "Hoje está sol",
            "Amanhã irá ficar nublado",
            "Ontem choveu",
            "Amanhã ficará nublado"
        )

        val randomIndex = (0 .. 4).random()
        val randomQuote = findViewById<TextView>(R.id.textViewFrase)
        randomQuote.text = quotes[randomIndex]
    }
}