package com.example.heroeslist

import Hero
import HeroesAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MasterActivity : AppCompatActivity() {

    private lateinit var recyclerViewHeroes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_master)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerViewHeroes = findViewById(R.id.heroesRV)
        recyclerViewHeroes.adapter = HeroesAdapter(this.createHeroes(), this, {
            Toast.makeText(this, "Hero: ${it.heroName}", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("HERO", it)
            startActivity(intent)
        })
        recyclerViewHeroes.layoutManager = LinearLayoutManager(this)
        recyclerViewHeroes.setHasFixedSize(true)
        recyclerViewHeroes.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun createHeroes() : List<Hero> {
        return listOf(
            Hero(R.drawable.batman, "Batman", "DC Comics", "Mestre em artes marciais, intelecto genial e equipamentos tecnológicos de ponta."),
            Hero(R.drawable.flash, "Flash", "DC Comics", "Velocidade sobre-humana, reflexos acelerados e capacidade de atravessar objetos."),
            Hero(R.drawable.hulk, "Hulk", "Marvel Entertainment", "Força física virtualmente ilimitada, resistência extrema e regeneração rápida."),
            Hero(R.drawable.superman, "Superman", "DC Comics", "Superforça, voo, invulnerabilidade, visão de calor e sopro congelante."),
            Hero(R.drawable.drstrange, "Dr. Strange", "Marvel Entertainment", "Manipulação de energia mágica, teletransporte e controle do tempo com o Olho de Agamotto."),
            Hero(R.drawable.ironman, "Ironman", "Marvel Entertainment", "Armadura motorizada que concede voo, força aumentada e diversos armamentos tecnológicos.")
        )
    }



}