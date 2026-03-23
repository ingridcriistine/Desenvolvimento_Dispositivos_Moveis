package com.example.calculadoraimc

import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    lateinit var imcEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        imcEditText = findViewById(R.id.imcEditText)
        val faixaTextView = findViewById<TextView>(R.id.faixaTextView)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        if (bundle != null)
        {
            val paciente = if (Build.VERSION.SDK_INT >= 33) {
                bundle.getParcelable("paciente", User::class.java)
            } else {
                bundle.getParcelable("paciente")
            }
            println(paciente?.nome)
            println(paciente?.altura)
            val result = calcularIMC(paciente?.peso, paciente?.altura)
            val imcFormatado = String.format("%.2f", result)
            imcEditText.setText(imcFormatado)

            val status = getStatusIMC(result)



            faixaTextView.text = "Faixa: ${status[0]}"
            statusTextView.text = status[1]

        }
    }

    fun calcularIMC(peso: Double?, altura: Double?): Double {
        if (peso == null || altura == null) return 0.0
        val imc = peso / (altura * altura)
        return imc
    }

    fun getStatusIMC(imc: Double): Array<String> {
        var faixa = ""
        var status = ""

        when {
            imc < 15 -> {
                faixa = "Abaixo de 15"
                status = "Extremamente abaixo do peso"
            }
            imc < 16 -> {
                faixa = "15 - 16"
                status = "Gravemente abaixo do peso"
            }
            imc < 18.5 -> {
                faixa = "16 - 18.5"
                status = "Abaixo do peso ideal"
            }
            imc < 25 -> {
                faixa = "18.5 - 25"
                status = "Peso ideal"
            }
            imc < 30 -> {
                faixa = "25 - 30"
                status = "Sobrepeso"
            }
            imc < 35 -> {
                faixa = "30 - 35"
                status = "Obesidade grau I"
            }
            imc < 40 -> {
                faixa = "35 - 40"
                status = "Obesidade grau II (grave)"
            }
            else -> {
                faixa = "Acima de 40"
                status = "Obesidade grau III (mórbida)"
            }
        }

        return arrayOf(faixa, status)
    }
}