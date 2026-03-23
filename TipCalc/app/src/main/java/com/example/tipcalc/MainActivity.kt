package com.example.tipcalc

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var currentBillTotal: Double = 0.0
    var currentCustomPercent: Int = 18
    var currentPeopleNumber: Int = 1

    lateinit var tip10EditText: EditText
    lateinit var tip15EditText: EditText
    lateinit var tip20EditText: EditText
    lateinit var total10EditText: EditText
    lateinit var total15EditText: EditText
    lateinit var total20EditText: EditText
    lateinit var billEditText: EditText
    lateinit var tipCustomEditText: EditText
    lateinit var totalCustomEditText: EditText
    lateinit var customTipTextView: TextView
    lateinit var customPeopleTextView: TextView
    lateinit var customPeopleEditText: EditText
    lateinit var totalPessoaEditText: EditText
    lateinit var totalPessoaTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tableLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tip10EditText = findViewById(R.id.tip10EditText)
        tip15EditText = findViewById(R.id.tip15EditText)
        tip20EditText = findViewById(R.id.tip20EditText)
        total10EditText = findViewById(R.id.total10EditText)
        total15EditText = findViewById(R.id.total15EditText)
        total20EditText = findViewById(R.id.total20EditText)
        billEditText = findViewById(R.id.billEditText)
        tipCustomEditText = findViewById(R.id.tipCustomEditText)
        totalCustomEditText = findViewById(R.id.totalCustomEditText)
        customTipTextView = findViewById(R.id.customTipTextView)
        customPeopleTextView = findViewById(R.id.customPeopleTextView)
        totalPessoaEditText = findViewById(R.id.totalPessoaEditText)
        totalPessoaTextView = findViewById(R.id.totalPessoaTextView)

        var customSeekBar = findViewById<SeekBar>(R.id.seekBar)
        var peopleSeekBar = findViewById<SeekBar>(R.id.peopleSeekBar)

        currentCustomPercent = customSeekBar.progress
        billEditText.addTextChangedListener(billTextWatcher)
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener)

        currentPeopleNumber = peopleSeekBar.progress
        peopleSeekBar.setOnSeekBarChangeListener(customPeopleSeekBarListener)

    }

    @SuppressLint("DefaultLocale")
    fun updateStandard() {
        var tenPercentTip = currentBillTotal * 0.10
        var tenPercentTotal = currentBillTotal + tenPercentTip
        var fifteenPercentTip = currentBillTotal * 0.15
        var fifteenPercentTotal = currentBillTotal + fifteenPercentTip
        var twentyPercentTip = currentBillTotal * 0.2
        var twentyPercentTotal = currentBillTotal + twentyPercentTip

        tip10EditText.setText(String.format("%.2f", tenPercentTip))
        total10EditText.setText(String.format("%.2f", tenPercentTotal))
        tip15EditText.setText(String.format("%.2f", fifteenPercentTip))
        total15EditText.setText(String.format("%.2f", fifteenPercentTotal))
        tip20EditText.setText(String.format("%.2f", twentyPercentTip))
        total20EditText.setText(String.format("%.2f", twentyPercentTotal))
    }

    @SuppressLint("DefaultLocale")
    fun updateCustom() {
        customTipTextView.text = "$currentCustomPercent %"

        var customTipAmount = currentBillTotal * currentCustomPercent * .01
        var customTotalAmount = currentBillTotal + customTipAmount

        tipCustomEditText.setText(String.format("%.2f", customTipAmount))
        totalCustomEditText.setText(String.format("%.2f", customTotalAmount))
    }

    fun updateTotal() {
        totalPessoaTextView.text = "$currentPeopleNumber"

        var customTipAmount = currentBillTotal * currentCustomPercent * .01
        var customTotalAmount = currentBillTotal + customTipAmount
        var customTotalPerPerson = customTotalAmount / currentPeopleNumber

        totalPessoaEditText.setText(String.format("%.2f", customTotalPerPerson))
    }

    private val customSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            currentCustomPercent = progress
            updateCustom()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}

    }

    private val customPeopleSeekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            currentPeopleNumber = if (progress == 0) {
                1
            } else {
                progress
            }
            updateTotal()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    private val billTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                currentBillTotal = s.toString().toDouble()
            } catch (e: NumberFormatException) {
                currentBillTotal = 0.0
            }
            updateStandard()
            updateCustom()
            updateTotal()
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }
}
