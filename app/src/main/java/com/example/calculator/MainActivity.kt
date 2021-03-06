package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var inFixText: TextView
    private lateinit var resText: TextView
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initialize()

    }


    private fun initialize() {
        inFixText = viewBinding.infixExpression
        resText = viewBinding.res

        viewBinding.root.allViews.forEach { it ->
            if (it is Button) {
                it.setOnClickListener {
                    btnEvent(it as Button)
                }

            }
        }

        updateScreen()
    }


    private fun updateScreen() {
        inFixText.text = State.getInfixExpression()
        resText.text = State.getResult()

    }


    private fun clearScreen() {
        State.clearState()
        updateScreen()
    }


    private fun calculate() {
        State.calculate()
        updateScreen()
    }


    private fun backSpace() {
        State.removeLastChar()
        State.calculate()
        updateScreen()

    }


    private fun btnEvent(view: Button) {
        when (val text = view.text as String) {

            "CE" -> clearScreen()

            "=" -> calculate()

            else -> {
                State.updateInfixExpression(text)
                State.calculate()
                updateScreen()
            }


        }

        if (view.id == R.id.button || view.id == R.id.button19) {
            backSpace()

        }
    }


}