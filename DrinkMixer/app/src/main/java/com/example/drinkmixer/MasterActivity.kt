package com.example.drinkmixer

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MasterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listView)
        val drinkList = arrayOf("Batida de Sonho de Valsa", "Drink dos Deuses", "Quentão")

        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, drinkList
        )
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { adapterView, view, position, id ->
            val selecionado = drinkList[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("DRINK", selecionado)

            startActivity(intent)
        }


    }
}