package com.example.myapplication

import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.BottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class DialogSheet():DialogFragment() {
    private var titulo: Any? = null
    private var _binding: BottomsheetBinding? = null
    private  val binding : BottomsheetBinding get() = _binding!!

    private var callback: ResulDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        _binding = BottomsheetBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun setupView(){

      with(binding){
          titulo?.let { textTitle.text = it.toString()  }
          btnCancel.setOnClickListener {
              finishDialog(false)
          }
          btnAcpetar.setOnClickListener {
              finishDialog(true)
          }
      }
    }

    private fun finishDialog(showDialog:Boolean){
        callback?.onReculDialog(showDialog)
        dismiss()
    }

    override fun dismiss(){
        super.dismiss()
    }

    override fun onStart() {
        super.onStart()
       // dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        //dialog?.window.let {  BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialog)}
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bottom_sheet)
        dialog?.window?.setGravity(Gravity.BOTTOM)
        dialog?.setCancelable(false)

    }

    companion object {
        @JvmStatic fun newInstances(
            callback : ResulDialog,
            title: Any? = null
        ) = DialogSheet().apply {
            this.titulo = title
            this.callback = callback
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}