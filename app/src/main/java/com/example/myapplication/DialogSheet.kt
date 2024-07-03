package com.example.myapplication

import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.BottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogSheet : BottomSheetDialogFragment() {
    private var titulo: Any? = null
    private var _binding: BottomsheetBinding? = null
    private val binding: BottomsheetBinding get() = _binding!!

    private var callback: ResulDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheet: FrameLayout =
            dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!

        dialog!!.setCancelable(true)
        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.apply {
            peekHeight = resources.displayMetrics.heightPixels
            state = BottomSheetBehavior.STATE_EXPANDED
        }

        setupView()
    }

    override fun getTheme(): Int = R.style.TransparentBottomSheetDialogTheme

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setupView() {
        with(binding) {
            titulo?.let { textTile.text = it.toString() }
            btnCancelar.setOnClickListener {
                finishDialog(false)
            }
            btnAceptar.setOnClickListener {
                finishDialog(true)
            }
        }
    }

    private fun finishDialog(showDialog: Boolean) {
        callback?.onReculDialog(showDialog)
        dismiss()
    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCancelable(false)
    }

    companion object {
        @JvmStatic
        fun newInstances(
            callback: ResulDialog,
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