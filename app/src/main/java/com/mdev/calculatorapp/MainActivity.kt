package com.mdev.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

/*File name: CalculatorAPP
Author Name: Benny Baby
STUDENT ID : 200469127
App Description : Unique calculator APP for calculation
Version: Android Studio Dolphin | 2021.3.1 for Windows 64-bit */

class MainActivity : AppCompatActivity() {

    //instance members of the mainActivity
    var ResultLabel : TextView? = null
    var result: Float = 0.0f
    var lhs: String = ""
    var rhs: String = ""
    var haveLHS: Boolean = false
    var haveRHS: Boolean = false
    var operation: String = ""
    var inputReady: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ResultLabel = findViewById<TextView>(R.id.Result_TextView)
    }


    //function for each button
    fun NumberButtons(view: View)
    {

        val buttonInfo = view as Button
        val buttonText = buttonInfo.text


        if ((ResultLabel?.text == "0") || !inputReady )
        {
            ResultLabel?.text = buttonText
            inputReady = true
        }
        else
        {
            if (buttonText == ".")
            {
                if(ResultLabel?.text?.contains(".") != true){

                    ResultLabel?.text = ResultLabel?.text.toString().plus(buttonText)
                }
            }
            else{
                ResultLabel?.text =  ResultLabel?.text.toString().plus(buttonText)
            }
        }

    }




    //function for all operation button
    fun OperatorButtons(view: View)
    {
        val buttonInfo = view as Button
        val buttonText = buttonInfo.text

        if( !inputReady) {
            ResultLabel?.text = result.toString()
            when (buttonText) {
                "=" -> Evalution()
            }

        }else {


            if (haveLHS) {
                rhs = ResultLabel?.text.toString()
                haveRHS = true
                inputReady = false
            } else {
                lhs = ResultLabel?.text.toString()
                haveLHS = true
                inputReady = false
            }
            if (haveLHS && haveRHS) {
                Evalution()
            }

            when (buttonText) {
                "+" -> operation = "+"
                "-" -> operation = "-"
                "X" -> operation = "*"
                "/" -> operation = "/"
//                "=" -> Evalution()
            }
        }
    }

    //function for clear button and backspace button
    fun ExtraButton(view: View){

        val buttonInfo = view as Button
        val buttonText = buttonInfo.text

        if(buttonText == "AC"){

            ResultLabel?.text = ResultLabel?.text.toString().drop(100)
            ResultLabel?.text == "0"
            ResetEverything()
        }
        else{
            if(ResultLabel?.text?.count()==1){
                ResultLabel?.text = "0"
            }
            else{
                ResultLabel?.text = ResultLabel?.text.toString().dropLast(1)
            }
        }

    }

    //Calculator evaluator function
    fun ResetEverything()
    {
        result=0.0f
        lhs = ""
        rhs = ""
        operation = ""
        haveLHS = false
        haveRHS = false
        inputReady = true

    }

    //function for each operation
    fun Addition(lhs: Float, rhs: Float): Float
    {
        return lhs + rhs
    }
    fun Subtraction(lhs: Float, rhs: Float): Float
    {
        return lhs - rhs
    }
    fun Division(lhs: Float, rhs: Float): Float
    {
        return lhs / rhs
    }
    fun Multiplication(lhs: Float, rhs: Float): Float
    {
        return lhs * rhs
    }

    //function for evaluation each of the operation
    fun Evalution()
    {
        when(operation){
            "+" -> result = Addition(lhs.toFloat(), rhs.toFloat())
            "-" -> result = Subtraction(lhs.toFloat(), rhs.toFloat())
            "/" -> result = Division(lhs.toFloat(), rhs.toFloat())
            "*" -> result = Multiplication(lhs.toFloat(), rhs.toFloat())

        }
        ResultLabel?.text = result.toString()
        lhs = result.toString()
        rhs = ""
        haveRHS = false


    }


}