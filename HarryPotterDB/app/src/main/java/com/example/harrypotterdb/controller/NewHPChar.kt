package com.example.harrypotterdb.controller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.harrypotterdb.R
import com.example.harrypotterdb.data.dao.HPCharDAO
import com.example.harrypotterdb.model.HPChar

class NewHPChar : AppCompatActivity() {

    private lateinit var hpCharDAO: HPCharDAO
    private var hpCharID: Int = 0
    private lateinit var etName: EditText
    private lateinit var etHouse: EditText
    private lateinit var etAncestry: EditText
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_hpchar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        hpCharDAO = HPCharDAO(this)

        etName = findViewById(R.id.etName)
        etHouse = findViewById(R.id.etHouse)
        etAncestry = findViewById(R.id.etAncestry)
        btnDelete = findViewById(R.id.btnDelete)

        // Verifica se estamos em modo de edição (personagem existente)
        hpCharID = intent.getIntExtra("charId", 0)
        if (hpCharID != 0) {
            btnDelete.visibility = Button.VISIBLE
            editChar()
        }
    }

    fun saveChar(view: View) {
        if (etName.text.isNotEmpty() || etHouse.text.isNotEmpty() || etAncestry.text.isNotEmpty()) {
            if (hpCharID == 0) {
                // Inserção de um novo personagem
                val newChar = HPChar(
                    name = etName.text.toString(),
                    house = etHouse.text.toString(),
                    ancestry = etAncestry.text.toString()
                )
                hpCharDAO.addChar(newChar)
                Toast.makeText(this, "Personagem adicionado com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                // Atualização de um personagem existente
                val updatedChar = HPChar(
                    id = hpCharID,
                    name = etName.text.toString(),
                    house = etHouse.text.toString(),
                    ancestry = etAncestry.text.toString()
                )
                hpCharDAO.updateChar(updatedChar)
                Toast.makeText(this, "Personagem atualizado com sucesso!", Toast.LENGTH_SHORT).show()
            }
            finish() // Volta para a MainActivity
        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }
    }

    // Carrega o personagem existente nos campos para edição
    private fun editChar() {
        val char = hpCharDAO.getCharById(hpCharID)
        char?.let {
            etName.setText(it.name)
            etHouse.setText(it.house)
            etAncestry.setText(it.ancestry)
        }
    }

    fun deleteChar(view: View) {
        hpCharDAO.deleteChar(hpCharID)
        Toast.makeText(this, "Personagem excluído com sucesso!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
