package com.example.cronometro

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var tvTime: TextView
    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTime = findViewById(R.id.tvTime)
        btnStart = findViewById(R.id.btnStart)

        // Define a ação do botão
        btnStart.setOnClickListener {
            startCounter()
        }
    }

    private fun startCounter() {
        // Inicia a corrotina no escopo do ciclo de vida
        lifecycleScope.launch(Dispatchers.Main) {
            // atualiza o contador de 1 até 10
            for (i in 1..10) {
                tvTime.text = i.toString() // atualiza o textView com o valor atual
                delay(1000L) // espera 1 segundo
            }
        }
    }

}