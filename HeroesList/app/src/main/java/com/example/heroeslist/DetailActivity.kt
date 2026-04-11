package com.example.heroeslist

import Hero
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val hero = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("HERO", Hero::class.java)
        } else {
            intent.getSerializableExtra("HERO") as Hero?
        }

        if(hero != null) {
            val imagem = findViewById<ImageView>(R.id.detailImageView)
            val nome = findViewById<TextView>(R.id.nameTextView)
            val poderes = findViewById<TextView>(R.id.poderesTextView)

            nome.text = hero.heroName
            poderes.text = hero.heroPower
            imagem.setImageResource(hero.heroImage)
        } else {
            Toast.makeText(this, "Não foi possível carregar o herói", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}