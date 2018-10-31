package com.example.archanavishwakarma.kotlinsimplecalcapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {
    lateinit var result: String

    private var pendinOperation: String = "=";
    private var operand1: Double? = null
    private var operand2: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var listener = View.OnClickListener { v ->
            var view = v as Button
            newNumberEditText.append(view.text)
        }
        var opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            var value = newNumberEditText.text.toString()
            if (value.isNotEmpty()) {
                performOperation(value, op)
            }
            pendinOperation = op
            operationTextView.text = pendinOperation


        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)

        buttonDot.setOnClickListener(listener)

        buttonPlus.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonMultiplication.setOnClickListener(opListener)
        buttonDivision.setOnClickListener(opListener)

        buttonEquals.setOnClickListener(opListener)

    }

    private fun performOperation(value: String, op: String) {
        if (operand1 == null) {
            operand1 = value.toDouble()
        } else {
            operand2 = value.toDouble()
            if (pendinOperation == "=") {
                pendinOperation = op
                if (pendinOperation == "=") {
                    operand1 = value.toDouble()
                }
            }
            when(pendinOperation){
                "=" -> operand1 = operand2
                "/" -> if(operand2 == 0.0){
                    operand1 = Double.NaN
                }else{
                    operand1 = operand1!! / operand2
                }
                "*"-> operand1 = operand1!! * operand2
                "-"-> operand1 = operand1!! - operand2
                "+"-> operand1 = operand1!! + operand2
            }
        }

         resultEditText.setText(operand1.toString())
         newNumberEditText.setText("")
    }




}
