package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.myapplication.databinding.FragmentOtpBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class OtpFragment :  Fragment() {


    private  var _binding : FragmentOtpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews()= with(binding){
        val otpEditTexts = arrayOf(etOtp1, etOtp2, etOtp3, etOtp4, etOtp5, etOtp6)

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

        btnShowDialog.setOnClickListener {

             DialogSheet.newInstances(
                 callback = object :ResulDialog {
                     override fun onReculDialog(showDialog: Boolean) {
                      Log.d("Valordialgo", "$showDialog")
                     }

                 },
                 title = "Ejemplo "
             ).show(childFragmentManager, "")

            //val buttonShwwtDialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialog)
            //buttonShwwtDialog.setContentView(R.layout.bottomsheet)
            //buttonShwwtDialog.show()

        }

        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}