package com.example.drinkmixer

import android.os.Bundle
import android.widget.TextView
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
        
        val drink = intent.getStringExtra("DRINK")
        
        val nome = findViewById<TextView>(R.id.nomeTextView)
        val ingredientes = findViewById<TextView>(R.id.ingredientesTextView)
        val preparo = findViewById<TextView>(R.id.preparoTextView)

        when (drink) {
            "Batida de Sonho de Valsa" -> {
                nome.text = "Batida de Sonho de Valsa"
                ingredientes.text = "• 3 bombons Sonho de Valsa\n• 1 lata de leite condensado\n• 1/2 lata (medida) de vodka\n• 1 lata de guaraná\n• Gelo a gosto"
                preparo.text = "1. Coloque os bombons, o leite condensado e a vodka no liquidificador.\n2. Bata bem até ficar homogêneo.\n3. Adicione o guaraná e gelo.\n4. Bata rapidamente apenas para misturar e sirva."
            }
            "Drink dos Deuses" -> {
                nome.text = "Drink dos Deuses"
                ingredientes.text = "• 1 lata de leite condensado\n• 1 lata de suco de uva concentrado\n• 1 lata (medida) de vodka\n• Gelo a gosto"
                preparo.text = "1. Coloque o leite condensado, o suco de uva e a vodka no liquidificador.\n2. Adicione gelo a gosto.\n3. Bata bem até obter uma mistura cremosa.\n4. Sirva imediatamente."
            }
            "Quentão" -> {
                nome.text = "Quentão"
                ingredientes.text = "• 600ml de cachaça\n• 2 xícaras de açúcar\n• 2 xícaras de água\n• 50g de gengibre fatiado\n• Cravo e canela em pau a gosto\n• Casca de 1 laranja"
                preparo.text = "1. Em uma panela, coloque o açúcar, o gengibre, o cravo e a canela.\n2. Leve ao fogo e mexa até o açúcar caramelizar.\n3. Adicione a água e deixe ferver até dissolver o açúcar.\n4. Junte a cachaça e deixe cozinhar por 15 minutos.\n5. Sirva bem quente."
            }
            else -> {
                nome.text = "Drink não encontrado"
                ingredientes.text = ""
                preparo.text = ""
            }
        }
    }
}
