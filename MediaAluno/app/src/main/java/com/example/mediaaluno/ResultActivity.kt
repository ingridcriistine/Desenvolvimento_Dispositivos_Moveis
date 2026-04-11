package com.example.mediaaluno

import Aluno
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.round

class ResultActivity : AppCompatActivity() {

    lateinit var nomeAluno: TextView
    lateinit var mediaFinal: TextView
    lateinit var freqFinal: TextView
    lateinit var situacao: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        nomeAluno = findViewById(R.id.alunoEditText)
        mediaFinal = findViewById(R.id.mediaEditText)
        freqFinal = findViewById(R.id.freqFinalEditText)
        situacao = findViewById(R.id.situacaoEditText)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        if (bundle != null)
        {
            val aluno = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable("aluno", Aluno::class.java)
            } else {
                bundle.getSerializable("aluno")
            }

            if(aluno != null) {
                situacaoFinal(aluno as Aluno)
                nomeAluno.text = aluno.nome
                freqFinal.text = "${aluno.freq}%"

                val media = (aluno.nota1 + aluno.nota2) / 2.0
                mediaFinal.text = String.format("%.1f", media)
            }

        }
    }

    fun situacaoFinal(aluno: Aluno) {
        val mediaFinal = (aluno.nota1 + aluno.nota2) / 2.0

        when {
            mediaFinal >= 7 && aluno.freq >= 75 -> {
                situacao.text = "Aprovado"
            }

            mediaFinal >= 4 && mediaFinal <= 6.9 && aluno.freq >= 75 -> {
                situacao.text = "Final"
            }

            mediaFinal < 4 -> {
                situacao.text = "Reprovado por nota"
            }

            aluno.freq < 75 -> {
                situacao.text = "Reprovado por frequência"
            }

            else -> {
                situacao.text = "Erro"
            }
        }
    }
}