package com.test.a1.ui

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.test.a1.R

fun setTheme(isDark: Boolean, context: Context, vararg textViews: TextView) {
    if (isDark) {
        textViews.forEach {
            it.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.black
                )
            )
        }
    }else{
        textViews.forEach {
            it.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }
    }
}