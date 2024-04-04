package com.omerfpekgoz.movieapp.presentation.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import com.omerfpekgoz.movieapp.R

/**
 * Created by omerfarukpekgoz on 4.04.2024.
 */
class ButtonCustomView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null
) : MaterialButton(context, attributeSet) {
    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomMaterialButton)
        val text = typedArray.getString(R.styleable.CustomMaterialButton_btnText)
        val textSize = typedArray.getDimension(R.styleable.CustomMaterialButton_btnTextSize, 20F)
        val icon = typedArray.getDrawable(R.styleable.CustomMaterialButton_btnIcon)
        val textColor = typedArray.getColor(R.styleable.CustomMaterialButton_btnTextColor, Color.BLACK)
        val backgroundColor = typedArray.getColor(R.styleable.CustomMaterialButton_btnBackgroundColor, Color.WHITE)
        val cardRadius = typedArray.getInt(R.styleable.CustomMaterialButton_btnCornerRadius, 20)
        setText(text)
        setTextSize(textSize)
        setIcon(icon)
        setTextColor(textColor)
        setBackgroundColor(backgroundColor)
        cornerRadius = cardRadius
        typedArray.recycle()
    }

}