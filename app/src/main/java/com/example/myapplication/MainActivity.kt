package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
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

        val otpEditTexts = arrayOf(
            findViewById<EditText>(R.id.et_otp_1),
            findViewById<EditText>(R.id.et_otp_2),
            findViewById<EditText>(R.id.et_otp_3),
            findViewById<EditText>(R.id.et_otp_4),
            findViewById<EditText>(R.id.et_otp_5),
            findViewById<EditText>(R.id.et_otp_6)
        )

        for (i in otpEditTexts.indices) {
            otpEditTexts[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpEditTexts.size - 1) {
                        otpEditTexts[i + 1].requestFocus()
                    } else if (s?.length == 0 && i > 0) {
                        otpEditTexts[i - 1].requestFocus()
                    }
                    val opt = otpEditTexts.get(0).text.toString()+otpEditTexts.get(1).text.toString()+otpEditTexts.get(2).text.toString()+otpEditTexts.get(3).text.toString()+otpEditTexts.get(4).text.toString()+otpEditTexts.get(5).text.toString()
                    Log.d("Otp", "GetOpt "+opt )
                }

                override fun afterTextChanged(s: Editable?) {

                    Log.d("Otp", "GetOptAfter ${s}")
                }
            })
        }

    }
}