package com.example.myapplication.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.myapplication.databinding.ToolbarcomponentBinding
import com.google.android.material.appbar.AppBarLayout

class toolbarComponent(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {


    private var binding = ToolbarcomponentBinding.inflate(LayoutInflater.from(context))

    init {
        binding.root.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        addView(binding.root)
    }

    fun setTitle(title:String){
        binding.title.text = title
    }

    fun setOnClickBack(task:()-> Unit){
        binding.btnBack.setOnClickListener {
            task.invoke()
        }
    }
}