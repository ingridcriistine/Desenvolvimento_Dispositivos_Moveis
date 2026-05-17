package com.example.catfact

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var catFactApi: CatFactApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(_root_ide_package_.retrofit2.converter.gson.GsonConverterFactory.create())
            .build()

        catFactApi = retrofit.create(CatFactApi::class.java)
    }

    fun getFact(view: View) {
        progressBar.visibility = View.VISIBLE
        tvResult.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val factResponse = withContext(Dispatchers.IO) {
                    catFactApi.getCatFact()
                }
                progressBar.visibility = View.GONE
                tvResult.visibility = View.VISIBLE

                tvResult.text = factResponse.fact
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching cat fact", e)
                tvResult.text = "Error fetching cat fact"
            }
        }
    }
}