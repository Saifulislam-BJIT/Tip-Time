package com.saiful.tiptime

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.saiful.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        Log.d(TAG, "Click Button")
        val cost = binding.costOfService.text.toString().toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            displayTip(0.0, 0.0)
            return
        }

        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
            R.id.optionEighteenPercent -> 0.18
            R.id.optionFifteenPercent -> 0.15
            R.id.optionTwentyPercent -> 0.20
            else -> 0.0
        }

        var tip = cost * tipPercentage

        if (binding.roundUpTip.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(cost, tip)

    }

    private fun displayTip(cost: Double, tip: Double) {
        val formattedCostOfService = NumberFormat.getCurrencyInstance().format(cost)
        binding.costOfAmount.text = getString(R.string.cost_of_amount, formattedCostOfService)

        val formattedTipAmount = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTipAmount)

        val formattedTotalAmount = NumberFormat.getCurrencyInstance().format(cost + tip)
        binding.totalAmount.text = getString(R.string.total_amount, formattedTotalAmount)
    }

}