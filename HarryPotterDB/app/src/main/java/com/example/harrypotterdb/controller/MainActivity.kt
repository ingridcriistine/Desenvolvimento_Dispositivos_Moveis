package com.example.harrypotterdb.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.harrypotterdb.R
import com.example.harrypotterdb.data.dao.HPCharDAO
import com.example.harrypotterdb.model.HPChar

class MainActivity : AppCompatActivity() {

    private lateinit var hpCharDAO: HPCharDAO
    private lateinit var listView: ListView
    private lateinit var emptyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView = findViewById(R.id.lvChars)
        emptyTextView = findViewById(R.id.tvEmpty)
        hpCharDAO = HPCharDAO(this)

        listAllChars()

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedChar = parent.getItemAtPosition(position) as HPChar
            val intent = Intent(this, NewHPChar::class.java).apply {
                putExtra("charId", selectedChar.id)
            }
            startActivity(intent)
        }
    }

    // Carrega a lista de personagens no ListView
    private fun listAllChars() {
        val hpChars = hpCharDAO.getAllChars()
        if (hpChars.isEmpty()) {
            listView.visibility = ListView.GONE
            emptyTextView.visibility = TextView.VISIBLE
        } else {
            listView.visibility = ListView.VISIBLE
            emptyTextView.visibility = TextView.GONE
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, hpChars)
            listView.adapter = adapter
        }
    }

    fun newChar(view: View) {
        val intent = Intent(this, NewHPChar::class.java)
        startActivity(intent)
    }

    // Atualiza a lista quando voltar de outra activity (adição, edição, etc.)
    override fun onResume() {
        super.onResume()
        listAllChars()
    }
}