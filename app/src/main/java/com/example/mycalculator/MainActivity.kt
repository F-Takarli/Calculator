package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNumberClicked()
        onOperatorClicked()
    }

    private fun onOperatorClicked() {
        binding.btnJam.setOnClickListener {
            if (binding.txtExpression.text.toString().isNotEmpty()){
                val myChar=binding.txtExpression.text.last()
                if (myChar !='+' && myChar !='-' && myChar !='*' && myChar !='/' )
                    appendText("+")
            }

        }
        binding.btnMenha.setOnClickListener {
            if (binding.txtExpression.text.toString().isNotEmpty()){
                val myChar=binding.txtExpression.text.last()
                if (myChar !='+' && myChar !='-' && myChar !='*' && myChar !='/' )
                    appendText("-")
            }


        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtExpression.text.toString().isNotEmpty()){
                val myChar=binding.txtExpression.text.last()
                if (myChar !='+' && myChar !='-' && myChar !='*' && myChar !='/' )
                    appendText("*")
            }

        }
        binding.btnTaghsim.setOnClickListener {
            if (binding.txtExpression.text.toString().isNotEmpty()){
                val myChar=binding.txtExpression.text.last()
                if (myChar !='+' && myChar !='-' && myChar !='*' && myChar !='/' )
                    appendText("/")
            }

        }
        binding.btnMosavi.setOnClickListener {
            try{
                val expression= ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result=expression.evaluate()
                val longResult=result.toLong()
                if (result==longResult.toDouble()){
                    binding.txtJavab.text=longResult.toString()
                }else {
                    binding.txtJavab.text=result.toString()
                }
            }catch (e:Exception){
                Toast.makeText(this,"خطا: عبارت وارد شده قابل محاسبه نیست!", Toast.LENGTH_LONG).show()}

        }
        binding.btnParantezBaste.setOnClickListener {
            appendText(")")
        }
        binding.btnParantezBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnDelete.setOnClickListener {
            val oldtext=binding.txtExpression.text.toString()
            if(oldtext.isNotEmpty()){
                binding.txtExpression.text=oldtext.substring(0,oldtext.length-1)
            }
        }
        binding.btnAC.setOnClickListener {
            binding.txtExpression.text=""
            binding.txtJavab.text=""
        }
        binding.btnDot.setOnClickListener {
            if(binding.txtExpression.text.toString().isEmpty() || binding.txtJavab.text.toString().isNotEmpty()) {
                appendText("0.")
            }else if(!binding.txtExpression.text.contains(".")){
                appendText(".")
            }

        }

    }

    private fun onNumberClicked() {
        binding.btn0.setOnClickListener {
            if(binding.txtExpression.text.toString().isNotEmpty()){
                appendText("0")
            }

        }
        binding.btn1.setOnClickListener {
            appendText("1")
        }
        binding.btn2.setOnClickListener {
            appendText("2")
        }
        binding.btn3.setOnClickListener {
            appendText("3")
        }

        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }
        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }
    }

    private fun appendText(newString: String) {
        if (binding.txtJavab.text.toString().isNotEmpty()) {
            binding.txtExpression.text = ""
        }
        binding.txtJavab.text = ""
        binding.txtExpression.append(newString)

        val viewTree: ViewTreeObserver =binding.horizontalScrollTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener (object: ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                binding.horizontalScrollTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.horizontalScrollTxtExpression.scrollTo(binding.txtExpression.width,0)
            }

        })

    }
}