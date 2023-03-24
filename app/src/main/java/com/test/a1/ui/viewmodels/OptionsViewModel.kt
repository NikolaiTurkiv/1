package com.test.a1.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.test.a1.R
import com.test.a1.domain.SharedUseCase
import javax.inject.Inject

class OptionsViewModel @Inject constructor(
    private val optionsUseCase: SharedUseCase
) : ViewModel() {

    val wallpaper = optionsUseCase.backgroundWallpaper
    val isDarkTheme = optionsUseCase.isDarkTheme

    fun changeTheme(isDark: Boolean) {
        optionsUseCase.saveTheme(isDark)
    }

    fun changeWallpaper(): Int {
        val background =
            arrayOf(
                R.drawable.background_1,
                R.drawable.background_2,
                R.drawable.background_3
            )

        var position = 0

        background.forEachIndexed { index, i ->
            if (i == optionsUseCase.backgroundWallpaper)
                position = index
        }

        if (position < background.size - 1) {
            position++
             optionsUseCase.saveBackground(background[position])
        } else {
            position = 0
             optionsUseCase.saveBackground(background[position])
        }
        Log.d("backgroundWallpaper",optionsUseCase.backgroundWallpaper.toString())
        return background[position]

    }

}