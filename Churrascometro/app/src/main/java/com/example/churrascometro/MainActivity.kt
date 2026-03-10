package com.example.churrascometro

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var sbMen = findViewById<SeekBar>(R.id.seekBarMen)
        var menQtd = findViewById<TextView>(R.id.textViewMen)

        var sbWomen = findViewById<SeekBar>(R.id.seekBarWomen)
        var womenQtd = findViewById<TextView>(R.id.textViewWomen)

        sbMen?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                menQtd.text = progress.toString()
                calculate(progress, sbWomen.progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        sbWomen?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                womenQtd.text = progress.toString()
                calculate(sbMen.progress, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    fun calculate(menQtd: Int, womenQtd: Int) {
        val outputSausage = findViewById<TextView>(R.id.outputSausage)
        val outputMeat = findViewById<TextView>(R.id.outputMeat)
        val sausageQtd: Double = (menQtd * 250.0 + womenQtd * 150.0)/1000
        val meatQtd: Double = (menQtd * 450.0 + womenQtd * 300.0)/1000
        outputSausage.text = sausageQtd.toString() + "Kg"
        outputMeat.text = meatQtd.toString() + "Kg"
    }
}